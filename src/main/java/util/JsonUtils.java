package util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import mq.MyProducer;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author pan.zhou
 *
 */
public class JsonUtils {
	private  Logger logger = Logger.getLogger(MyProducer.class);
	/**
	 * 
	 */
	private static ObjectMapper objectMapper = new ObjectMapper();

	static {
		objectMapper.setSerializationInclusion(Include.NON_NULL);
	}

	/**
	 * 将java对象转换成json字符串
	 * 
	 * @param obj
	 *            准备转换的对象
	 * @return json字符串
	 * @throws Exception
	 */
	public static String beanToJson(Object obj) {
		try {
			String json = objectMapper.writeValueAsString(obj);
			return json;
		} catch (Exception e) {
			String errorMessage = "convert Bean to Json Error";
			throw new RuntimeException(e);
		}
	}
	/**
	 * 将json字符串转换成java list对象
	 * @param json  准备转换的json字符串
	 * @param elementClasses 准备转换的类
	 * @param <T>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> jsonToList(String json, Class<T> elementClasses) {
		try {
			JavaType javaType = objectMapper.getTypeFactory()
					.constructParametricType(ArrayList.class, elementClasses);
			return (List<T>) objectMapper.readValue(json, javaType);
		} catch (Exception e) {
			String errorMessage = "convert Json to List Error";
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将json字符串转换成java map对象
	 * @param json 准备转换的json字符串
	 * @param keyClasses keyclass
	 * @param valueClasses valueclass
	 * @param <K>
	 * @param <V>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> jsonToMap(String json, Class<K> keyClasses,
                                             Class<V> valueClasses) {
		try {
			JavaType javaType = objectMapper.getTypeFactory()
					.constructParametricType(HashMap.class, keyClasses,
							valueClasses);
			return (Map<K, V>) objectMapper.readValue(json, javaType);
		} catch (Exception e) {
			String errorMessage = "convert Json to Map Error";
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将java对象转换成json字符串
	 *
	 * @param obj
	 *            准备转换的对象
	 * @return json字符串
	 * @throws Exception
	 */
	public static String beanToJson(Object obj, boolean notNull) {
		try {
			String json = objectMapper.writeValueAsString(obj);
			return json;
		} catch (Exception e) {
			String errorMessage = "convert bean to json Error";
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将json字符串转换成java对象
	 *
	 * @param json
	 *            准备转换的json字符串
	 * @param cls
	 *            准备转换的类
	 * @return
	 * @throws Exception
	 */
	public static Object jsonToBean(String json, Class<?> cls) {
		try {
			Object bean = objectMapper.readValue(json, cls);
			return bean;
		} catch (Exception e) {
			String errorMessage = "convert json to bean Error";
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将json字节数组转换成java对象
	 * @param jsonByte 准备转换的json字符串
	 * @param cls 准备转换的类
	 * @return
	 */
	public static Object jsonToBean(byte[] jsonByte, Class<?> cls) {
		try {
			Object bean = objectMapper.readValue(jsonByte, cls);
			return bean;
		} catch (Exception e) {
			String errorMessage = "convert json to bean Error";
			throw new RuntimeException(e);
		}
	}
	/**
	 * 将json字节数组转换成java对象
	 * @param jsonByte 准备转换的json字符串
	 * @param cls 准备转换的类
	 * @param <T>
	 * @return
	 */
	public static <T> T jsonToRealBean(byte[] jsonByte, Class<T> cls) {
		try {
			T bean = objectMapper.readValue(jsonByte, cls);
			return bean;
		} catch (Exception e) {
			String errorMessage = "convert json to RealBean Error";
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将json字节数组转换成java对象
	 *
	 * @param json
	 *            准备转换的json字符串
	 * @param cls
	 *            准备转换的类
	 * @return
	 * @throws Exception
	 */
	public static <T> T jsonToRealBean(String json, Class<T> cls) {
		try {
			T bean = objectMapper.readValue(json, cls);
			return bean;
		} catch (Exception e) {
			String errorMessage = "convert json to RealBean Error";
			throw new RuntimeException(e);
		}
	}

}