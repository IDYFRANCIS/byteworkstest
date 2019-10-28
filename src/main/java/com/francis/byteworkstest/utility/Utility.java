package com.francis.byteworkstest.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.fasterxml.jackson.databind.deser.std.DateDeserializers.DateDeserializer;
import com.francis.byteworkstest.constant.ServerResponseStatus;
import com.francis.byteworkstest.dto.ErrorResponse;
import com.francis.byteworkstest.dto.LoginResponse;
import com.francis.byteworkstest.dto.ServerResponse;
import com.francis.byteworkstest.dto.SignInRequest;
import com.google.common.hash.Hashing;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class Utility {
	
	public static int generateDigit (){
		Random random = new Random();
		int numb = random.nextInt(90000);
		return numb;
	}
	
	public static String generateRandomString(int length) {
        Random random = new Random();
        return random.ints(48, 122)
            .filter(i -> (i < 57 || i > 65) && (i < 90 || i > 97))
            .mapToObj(i -> (char) i)
            .limit(length)
            .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
            .toString();
    }
    
	public static int generateActivationCode(){
		Random random = new Random();
		int numb = random.nextInt(900000);
		return numb;
	}
	
	public static String nummberPadding4() {

		int size = 0;

		String pin = "";

		Random ra = new Random();

		long l1 = ra.nextLong() % 200000000;

		if (l1 < 0) {

			l1 = (-1) * l1;

		}

		long l2 = ra.nextLong() % 200000000;

		if (l2 < 0) {

			l2 = (-1) * l2;

		}

		long l3 = ra.nextLong() % 200000000;

		if (l3 < 0) {

			l3 = (-1) * l3;

		}

		long l4 = ra.nextLong() % 200000000;

		if (l4 < 0) {

			l4 = (-1) * l4;

		}



		String f = Long.toString(l1).toString() + Long.toString(l2).toString() + Long.toString(l3).toString()

				+ Long.toString(l4).toString();

		String cor = "1234567890987654321123456789" + getTodaysdatess();

		size = 3;

		pin = (f + cor).substring(0, size);

		String x_str = pin + getTodaysdatess();



		long new_x = Long.parseLong(x_str.substring(3));



		return new_x + "";

	}



	public static  long getTodaysdatess() {

		long date = System.currentTimeMillis();

		return date;



	}
	
	
	 public static boolean isNull(Object object) {
	        return (object == null);
	    }

	    public static ArrayList<Object> validateFields(Object[] fields, Object[] names) {
	        ArrayList<Object> errors = new ArrayList<Object>();
	        int i = 0;
	        for (Object field : fields) {
	            if (field == null || field.toString()
	                .isEmpty()
	                || field.toString()
	                    .equalsIgnoreCase(" ")) {
	                errors.add(names[i]);
	            }
	            i++;
	        }
	        return errors;
	    }
	    
	    
	    public static boolean isValidEmail(String email){
	        return EmailValidator.getInstance(true).isValid(email);
	    }
	    
	    public static boolean isValidPhone(String phone){
	        return phone.startsWith("+234") && phone.length() > 13 || phone.startsWith("070") && phone.length() > 9
	        		|| phone.startsWith("080") && phone.length() > 9 || phone.startsWith("090") && phone.length() > 9
	        		|| phone.startsWith("0") && phone.length() > 9;
	    }

	    @SuppressWarnings("unchecked")
	    public static <T> T[] arrayMerge(T[] array1, T[] array2, T[] array3) {
	        ArrayList<T> list = new ArrayList<T>();
	        list.addAll(Arrays.asList(array1));
	        list.addAll(Arrays.asList(array2));
	        list.addAll(Arrays.asList(array3));
	        T[] result = (T[]) Array.newInstance(array1.getClass()
	            .getComponentType(), list.size());
	        result = (T[]) list.toArray(result);
	        return result;
	    }

	    public static String arrayToString(ArrayList<Object> list) {
	        return list.toString()
	            .substring(1, list.toString()
	                .length() - 1);
	    }

	    public static Object[] objectArrayFromArrayList(ArrayList<Object> list) {
	        Object[] array = new Object[list.size()];
	        return list.toArray(array);
	    }

	    public static String capitalizeFirstLetter(String text) {
	        return text.substring(0, 1)
	            .toUpperCase()
	            .concat(text.substring(1, text.length()));
	    }

	    public static String today() {
	        return Calendar.getInstance()
	            .get(Calendar.YEAR) + "-"
	            + Calendar.getInstance()
	                .get(Calendar.MONTH)
	            + "-" + Calendar.getInstance()
	                .get(Calendar.DAY_OF_MONTH);
	    }

	    public static String hashPassword(Object password) {
	        return Hashing.sha512()
	            .hashString(password.toString(), Charset.defaultCharset())
	            .toString();
	    }

	    public static String toBase64(Object password) {
	        try {
	            return "Basic " + new String(Base64.getEncoder()
	                .encode(password.toString()
	                    .getBytes("utf-8")));
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	    public static boolean comparePasswords(String newPassword, String oldHash) {
	        return hashPassword(newPassword).equals(oldHash);
	    }

	    public static Date toDate(String dateString) {
	        Date date = new Date();
	        try {
	            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
	            date = format.parse(dateString);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        return date;
	    }

	    public static String toDate() {
	        String date = null;
	        try {
	            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	            date = format.format(new Date());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return date;
	    }
	    public static String toDateAndTime() {
	        String date = null;
	        try {
	        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
	            date = format.format(new Date());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return date;
	    }

	    public int getYearFromDate(String date) {
	        SimpleDateFormat format = new SimpleDateFormat("yyyy");
	        @SuppressWarnings("deprecation")
	        Date instantDate = new Date(date);
	        return Integer.parseInt(format.format(instantDate));
	    }

	    public static Date getDateFromLocalDate(LocalDate localDate) {
	        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault())
	            .toInstant());
	        return date;
	    }

	    public static Date toShortDate(String dateString) {
	        Date date = new Date();
	        try {
	            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	            date = format.parse(dateString);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        return date;
	    }

	    public Date removeTime(Date date) {
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        cal.set(Calendar.HOUR_OF_DAY, 0);
	        cal.set(Calendar.MINUTE, 0);
	        cal.set(Calendar.SECOND, 0);
	        cal.set(Calendar.MILLISECOND, 0);
	        return cal.getTime();
	    }
	    
	    
	    /**
	     * Login request method, this method login as oAuth login token requires basic authorization set 
	     * @param urlPath
	     * @param request
	     * @param authorization
	     * @return ServerResponse 
	     */
	    @SuppressWarnings("deprecation")
		public static ServerResponse loginHttpRequest2(String urlPath, SignInRequest request, String authorization) {
			try {
			
			ServerResponse response = new ServerResponse();
			
			URL url = new URL(urlPath);
			HttpURLConnection conn = (HttpURLConnection)
			
			url.openConnection();
	         
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Authorization", authorization);
			
			Gson gson = new Gson();
//			String input = data.toString();
			String input  = "grant_type=" + URLEncoder.encode(request.getGrant_type()) + 
					"&username=" + URLEncoder.encode(request.getUsername()) +
					"&password=" + URLEncoder.encode(request.getPassword()) ;
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			
			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				
				BufferedReader br = new BufferedReader(new
				
				
				InputStreamReader((conn.getInputStream())));
				String output;
				while ((output = br.readLine()) != null) {
				
					conn.disconnect();
					
					System.out.println("Request data: " + output);
					LoginResponse loginResponse = gson.fromJson(output, LoginResponse.class);
					response.setData(loginResponse);
					response.setMessage("Login Successful");
			        response.setSuccess(true);
					response.setStatus(ServerResponseStatus.OK);
					return response;
			
				}
			
			}else if (conn.getResponseCode() == HttpURLConnection.HTTP_BAD_REQUEST) {
				
				       BufferedReader br = new BufferedReader(new
						
						
						InputStreamReader((conn.getErrorStream())));
						String output;
						while ((output = br.readLine()) != null) {
							
							System.out.println(output);
							conn.disconnect();
							response.setData("Failed to sign in");
							response.setMessage("check your phone and password then try again or contact support");
					        response.setSuccess(false);
							response.setStatus(ServerResponseStatus.FAILED);
							return response;
					 
						}
			}else if (conn.getResponseCode() == HttpURLConnection.HTTP_UNAUTHORIZED) {
				
			       BufferedReader br = new BufferedReader(new
					
					
					InputStreamReader((conn.getErrorStream())));
					String output;
					while ((output = br.readLine()) != null) {
						
						System.out.println(output);
						conn.disconnect();
						response.setData("You are not authorize ");
						response.setStatus(ServerResponseStatus.FAILED);
						return response;
				 
					}
			}
				
			} catch (MalformedURLException e) {
			
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			
			}
				return null;
			
		}
		
		
	    /**
	     * Convert credential to basic auth Base64 String format
	     * @param username
	     * @param password
	     * @return String
	     */
		public static String getCredentials(String username, String password) {
	        String plainClientCredentials = username + ":" + password;
	        String base64ClientCredentials = new String(org.apache.commons.codec.binary.Base64.encodeBase64(plainClientCredentials.getBytes()));

	        String authorization = "Basic " + base64ClientCredentials;
	        return authorization;
	    } 
		
		
	    @SuppressWarnings("deprecation")
		public static ServerResponse loginHttpRequest(String urlPath, SignInRequest request, String authorization) {
				try {
					ServerResponse response = new ServerResponse();
				
				GsonBuilder gsonBuilder = new GsonBuilder();	
				Gson gson = gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer()).create();
				
				String input  = "grant_type=" + URLEncoder.encode(request.getGrant_type()) + 
						"&username=" + URLEncoder.encode(request.getUsername()) +
						"&password=" + URLEncoder.encode(request.getPassword()) ;
				
	            StringEntity postingString = new StringEntity(input);
	            
	            TrustManager[] trustAllCerts = new TrustManager[]{
	            		new X509TrustManager() {
	            		    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
	            		        return null;
	            		    }
	            		    public void checkClientTrusted(
	            		        java.security.cert.X509Certificate[] certs, String authType) {
	            		    }
	            		    public void checkServerTrusted(
	            		        java.security.cert.X509Certificate[] certs, String authType) {
	            		    }
	            		}};

	            		   try {
	            		    SSLContext sc = SSLContext.getInstance("SSL");
	            		    sc.init(null, trustAllCerts, new java.security.SecureRandom());
	            		    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	            		    } catch (Exception e) {
	            		    }

	            
	            HttpClient client = HttpClientBuilder.create().build();
	            HttpPost post = new HttpPost(urlPath);
	            post.setEntity(postingString);
	            post.addHeader("Content-type", "application/x-www-form-urlencoded");
	            post.addHeader("Authorization", authorization);
	            StringBuilder result = new StringBuilder();
	            HttpResponse responseData = client.execute(post);
	            
	            if (responseData.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_OK) {
	                BufferedReader rd = new BufferedReader(new InputStreamReader(responseData.getEntity().getContent()));

	                String line;
	                while ((line = rd.readLine()) != null) {
	                    result.append(line);
	                }
	                System.out.println("result: " + result);

	                LoginResponse loginResponse = gson.fromJson(result.toString(), LoginResponse.class);
	                
	                if ( loginResponse.getUser() != null && !loginResponse.getUser().isActive()) {
	                	
	                	response.setData("");
						response.setMessage("Activation required, please activate your account");
				        response.setSuccess(false);
						response.setStatus(ServerResponseStatus.FAILED);
		                return response;
					}
	                
					response.setData(loginResponse);
					response.setMessage("Login Successful");
			        response.setSuccess(true);
					response.setStatus(responseData.getStatusLine().getStatusCode());
	                return response;
	            } else {
	                BufferedReader rd = new BufferedReader(new InputStreamReader(responseData.getEntity().getContent()));

	                String line;
	                while ((line = rd.readLine()) != null) {
	                    result.append(line);
	                }
	                System.out.println("error result: " + result);
	    			
	                ErrorResponse errorResponse = gson.fromJson(result.toString(), ErrorResponse.class);
	                
	                response.setStatus(responseData.getStatusLine().getStatusCode());
	                response.setData("");
					response.setMessage(errorResponse.getError_description());
			        response.setSuccess(false);
					response.setStatus(ServerResponseStatus.FAILED);
	                return response;
	            }
					
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				
				}
			
				return null;
				
		    }
	    
}
