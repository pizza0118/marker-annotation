package com.demo.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.JSONException;
import org.reflections.Reflections;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.annotation.ApiInfo;
import com.demo.annotation.ApiModule;
import com.demo.annotation.vo.ApiInfoVO;
import com.demo.annotation.vo.ApiModuleVO;
import com.demo.annotation.vo.ApiVO;

public class ApiReflection {
	//Array and VO 預設值的數量
	static final Integer COLLECTION_ARRAY_DEFAULT_VALUE_COUNT = 2;
	static final String STRING_DEFAULT_VALUE = "string";
	static final Number NUMBER_DEFAULT_VALUE = 0;
	
	static public ApiVO apiInfo() {
		ApiVO apiVO = new ApiVO();
		try {
			//載入專案所有class
			Reflections reflections = new Reflections("");
			//取得有@ApiModule標記之Class
			Set<Class<?>> apiModuleClasses = reflections.getTypesAnnotatedWith(ApiModule.class);
			//取得有@ApiInfo標記之method
			for(Class<?> apiModuleClass : apiModuleClasses) {
				ApiModuleVO apiModuleVO = new ApiModuleVO();
				//取得ApiModule 資訊
				ApiModule apiModule = apiModuleClass.getAnnotation(ApiModule.class);
				String apiModuleUrl = apiModuleClass.getAnnotation(RequestMapping.class).value()[0];
				apiModuleVO.setValue(apiModule.value());
				for(Method api : apiModuleClass.getMethods()) {
					if(api.isAnnotationPresent(ApiInfo.class)) {
						ApiInfoVO apiInfoVO = new ApiInfoVO();
						//取得ApiInfo資訊
						ApiInfo apiInfo = api.getAnnotation(ApiInfo.class);
						String apiInfoUrl = api.getAnnotation(PostMapping.class).value()[0];
						
						//取得該Api input參數
						Parameter[] paramArray = api.getParameters();
						Map<String, Object> inputMap = new HashMap<>();
						//參數分類包裝
						for(Parameter param : paramArray) {
							searchField(param.getType().getDeclaredFields(), inputMap);
						}
						apiInfoVO.setValue(apiInfo.value());
						apiInfoVO.setDesc(apiInfo.desc());
						apiInfoVO.setUrl(apiModuleUrl + apiInfoUrl);
						apiInfoVO.setInputs(inputMap);
						apiModuleVO.getApiInfoVOList().add(apiInfoVO);
					}
				}
				apiVO.getApiModuleVOList().add(apiModuleVO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return apiVO;
	}
	
	/**
	 * 遞迴屬性判斷並放入Map 可找出多層屬性
	 * @param fields
	 * @param map
	 * @return
	 * @throws JSONException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	static private Map<String, Object> searchField(Field[] fields, Map<String, Object> map) throws JSONException, InstantiationException, IllegalAccessException {
		for(Field field : fields) {
			//屬性非基本類別
			if(!field.getType().isPrimitive()) {
				boolean isNotValueObject = 
							String.class.isAssignableFrom(field.getType())
						||  Number.class.isAssignableFrom(field.getType())
						||  Boolean.class.isAssignableFrom(field.getType())
						||  Character.class.isAssignableFrom(field.getType());
				
				//屬性為基本類別包裝類別
				if(isNotValueObject) {
					map.put(field.getName(), mapSetDefaultValue(field));
				}
				//屬性為Collection or 屬性為陣列
				else if(Collection.class.isAssignableFrom(field.getType()) || field.getType().isArray()) {
					map.put(field.getName(), mapSetDefaultValue(field));
				}
				//屬性為Value Object
				else {
					map.put(field.getName(), searchField(field.getType().getDeclaredFields(), new HashMap<>()));
				}
			}
			else {
				map.put(field.getName(), mapSetDefaultValue(field));
			}
		}
		return map;
	}
	
	/**
	 * 依照屬性型別給予預設值 若為迴圈陣列則給兩個長度當作預設值
	 * @param field
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws JSONException
	 */
	@SuppressWarnings("unchecked")
	static private <T> Object mapSetDefaultValue(Field field) throws InstantiationException, IllegalAccessException, JSONException {
		//字串類型
		if(String.class.isAssignableFrom(field.getType())) {
			return "string";
		}
		//數字類型
		if(Number.class.isAssignableFrom(field.getType())) {
			return 0;
		}
		//若此屬性為Collection類型
		if(Collection.class.isAssignableFrom(field.getType())) {
			//取得實際化參數類型(為了判斷可能出現之泛型)
			ParameterizedType paramType = (ParameterizedType) field.getGenericType();
			//取得CLASS
	        Class<T> typeClass = (Class<T>) paramType.getActualTypeArguments()[0];
	        return judgClassTypeSetDefaultValue(typeClass);
		}
		//若屬性為Array
		if(field.getType().isArray()) {
			Class<T> typeClass = (Class<T>) field.getGenericType();
			Class<?> arrayType = typeClass.getComponentType();
			return judgClassTypeSetDefaultValue(arrayType);
		}
		return null;
	}
	
	/**
	 * 依照常數數量給定預設值數量
	 * @param clazz
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws JSONException
	 */
	static private Object judgClassTypeSetDefaultValue(Class<?> clazz) throws InstantiationException, IllegalAccessException, JSONException {
		if(String.class.isAssignableFrom(clazz)) {
			String[] strArray = new String[COLLECTION_ARRAY_DEFAULT_VALUE_COUNT];
			for(int i = 0 ; i < strArray.length; i++) {
				strArray[i] = STRING_DEFAULT_VALUE;
			}
        	return strArray;
	    }
	    else if(Number.class.isAssignableFrom(clazz)) {
	    	Number[] intArray = new Number[COLLECTION_ARRAY_DEFAULT_VALUE_COUNT];
			for(int i = 0 ; i < intArray.length; i++) {
				intArray[i] = NUMBER_DEFAULT_VALUE;
			}
        	return intArray;
	    }
	    else {
	    	//取得該class的fields 並跑迴圈將底下所有屬性RUN過
	    	Field[] fields = clazz.getDeclaredFields();
	    	Object[] objArray = new Object[COLLECTION_ARRAY_DEFAULT_VALUE_COUNT];
			for(int i = 0 ; i < objArray.length; i++) {
				objArray[i] = searchField(fields, new HashMap<>());
			}
	    	return objArray;
	    }
	}
}
