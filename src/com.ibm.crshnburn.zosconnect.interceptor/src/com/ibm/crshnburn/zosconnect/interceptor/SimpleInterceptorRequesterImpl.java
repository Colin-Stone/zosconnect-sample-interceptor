package com.ibm.crshnburn.zosconnect.interceptor;

import java.util.Map;

import org.osgi.service.component.ComponentContext;

import com.ibm.zosconnect.spi.Data;
import com.ibm.zosconnect.spi.DataRequester;
import com.ibm.zosconnect.spi.HttpZosConnectRequest;
import com.ibm.zosconnect.spi.InterceptorException;
import com.ibm.zosconnect.spi.InterceptorRequester;

/**
 * The SimpleInterceptorRequestorImpl class is an example of an Interceptor that implements the InterceptorRequestor
 * interface.  See the AllPointsInterceptorSample for an example use of the ServiceProviderInterceptor and
 * EarlyFailureInterceptor interfaces.  See also the SimpleInterceptorSample for an example use of the 
 * Interceptor with standard API request.
 *
 * By implementing the InterceptorRequestor interface this Interceptor sample is invoked early when being 
 * processed by z/OS Connect EE, and again after the response has been returned and z/OS Connect EE is about 
 * to return to the caller. For simplification these points are known as P1, and P4. 
 *
 * Users should implement the preInvokeRequester and preInvokeResponse methods.
 *
 *
 * @author IBM
 */
public class SimpleInterceptorRequesterImpl implements InterceptorRequester {

	/**
     * The registered sequence number of this Interceptor which determines the order
     * in which the Interceptor is called in relation to other Interceptor's.
     */
    private int sequence;
    
    /**
     * Activates the Interceptor.
     *
     * Trace the activation and retrieve the Interceptor's sequence number from
     * the Interceptor's configuration element in server.xml.
     *
     * @param context
     * @param properties
     */
    protected void activate(ComponentContext context, Map<String, Object> properties) {

        System.out.println(getName() + " activated");
        if (properties.containsKey(CFG_AD_SEQUENCE_ALIAS)) {
            sequence = (Integer) properties.get(CFG_AD_SEQUENCE_ALIAS);
        }
    }

    /**
     * Deactivates the Interceptor.
     *
     * The Interceptor will no longer receive events.
     *
     * @param context
     */
    protected void deactivate(ComponentContext context) {
        System.out.println(getName() + " deactivated");
    }

    /**
     * Called to signal that the Interceptor's configuration element may have changed in server.xml.
     *
     * Trace the activation and retrieve the Interceptor's sequence number from
     * the Interceptor's configuration element in server.xml.
     *
     * @param context
     * @param properties
     */
    protected void modified(Map<String, Object> properties) {

        System.out.println(getName() + " modified");
        if (properties.containsKey(CFG_AD_SEQUENCE_ALIAS)) {
            sequence = (Integer) properties.get(CFG_AD_SEQUENCE_ALIAS);
        }
    }
    
	@Override
	public String getName() {
		return "zOSConnectReferenceInterceptorRequester";
	}

	@Override
	public int getSequence() {
		return sequence;
	}

	@Override
	public void postInvoke(Map<Object, Object> arg0, HttpZosConnectRequest arg1, Data arg2)
			throws InterceptorException {
		System.out.println(getName() + " postInvoke");

		System.out.println(getName() + " postInvoke exit");
	}

	@Override
	public void preInvoke(Map<Object, Object> arg0, HttpZosConnectRequest arg1, Data arg2) throws InterceptorException {
		System.out.println(getName() + " preInvoke");

		System.out.println(getName() + " preInvoke exit");
	}

	@Override
	public void preInvokeRequester(Map<Object, Object> requestStateMap, DataRequester data) throws InterceptorException {
		System.out.println(getName() + " preInvokeRequester entry ");
		
		System.out.println(getName() + " preInvokeRequester API_REQUESTER_DESCRIPTION " + data.getData(DataRequester.API_REQUESTER_DESCRIPTION));
		System.out.println(getName() + " preInvokeRequester API_REQUESTER_NAME " + data.getData(DataRequester.API_REQUESTER_NAME));
		System.out.println(getName() + " preInvokeRequester API_REQUESTER_VERSION " + data.getData(DataRequester.API_REQUESTER_VERSION));
		System.out.println(getName() + " preInvokeRequester AUTHORIZATION_ADMIN_GROUP_NAMES " + data.getData(DataRequester.AUTHORIZATION_ADMIN_GROUP_NAMES));
		System.out.println(getName() + " preInvokeRequester AUTHORIZATION_INVOKE_GROUP_NAMES " + data.getData(DataRequester.AUTHORIZATION_INVOKE_GROUP_NAMES));
		System.out.println(getName() + " preInvokeRequester AUTHORIZATION_OPERATIONS_GROUP_NAMES " + data.getData(DataRequester.AUTHORIZATION_OPERATIONS_GROUP_NAMES));
		System.out.println(getName() + " preInvokeRequester AUTHORIZATION_READER_GROUP_NAMES " + data.getData(DataRequester.AUTHORIZATION_READER_GROUP_NAMES));
		System.out.println(getName() + " preInvokeRequester ENDPOINT_IDENTIFIER " + data.getData(DataRequester.ENDPOINT_IDENTIFIER));
		System.out.println(getName() + " preInvokeRequester ENDPOINT_METHOD " + data.getData(DataRequester.ENDPOINT_METHOD));
		System.out.println(getName() + " preInvokeRequester ENDPOINT_QUERY_STRING " + data.getData(DataRequester.ENDPOINT_QUERY_STRING));
		System.out.println(getName() + " preInvokeRequester ENDPOINT_REFERENCE " + data.getData(DataRequester.ENDPOINT_REFERENCE));
		System.out.println(getName() + " preInvokeRequester ENDPOINT_FULL_PATH " + data.getData(DataRequester.ENDPOINT_FULL_PATH));
		System.out.println(getName() + " preInvokeRequester HTTP_RESPONSE_CODE " + data.getData(DataRequester.HTTP_RESPONSE_CODE));
		System.out.println(getName() + " preInvokeRequester REQUEST_ID " + data.getData(DataRequester.REQUEST_ID));
		System.out.println(getName() + " preInvokeRequester TIME_ENDPOINT_RECEIVED " + data.getData(DataRequester.TIME_ENDPOINT_RECEIVED));
		System.out.println(getName() + " preInvokeRequester TIME_ENDPOINT_SENT " + data.getData(DataRequester.TIME_ENDPOINT_SENT));
		System.out.println(getName() + " preInvokeRequester TIME_ZOS_CONNECT_ENTRY " + data.getData(DataRequester.TIME_ZOS_CONNECT_ENTRY));
		System.out.println(getName() + " preInvokeRequester TIME_ZOS_CONNECT_EXIT " + data.getData(DataRequester.TIME_ZOS_CONNECT_EXIT));
		System.out.println(getName() + " preInvokeRequester USER_NAME_ASSERTED " + data.getData(DataRequester.USER_NAME_ASSERTED));
		System.out.println(getName() + " preInvokeRequester USER_PRINCIPAL " + data.getData(DataRequester.USER_PRINCIPAL));
		System.out.println(getName() + " preInvokeRequester CICS_APPLID: " + data.getData(DataRequester.CICS_APPLID));
		System.out.println(getName() + " preInvokeRequester CICS_TASK_NUMBER: " + data.getData(DataRequester.CICS_TASK_NUMBER));
		System.out.println(getName() + " preInvokeRequester CICS_TRANSID: " + data.getData(DataRequester.CICS_TRANSID));
		System.out.println(getName() + " preInvokeRequester IMS_APPNAME: " + data.getData(DataRequester.IMS_APPNAME));
		System.out.println(getName() + " preInvokeRequester IMS_IDENTIFIER: " + data.getData(DataRequester.IMS_IDENTIFIER));
		System.out.println(getName() + " preInvokeRequester IMS_REGION_ID: " + data.getData(DataRequester.IMS_REGION_ID));
		System.out.println(getName() + " preInvokeRequester IMS_TRANSNAME: " + data.getData(DataRequester.IMS_TRANSNAME));
		System.out.println(getName() + " preInvokeRequester MVS_ASID: " + data.getData(DataRequester.MVS_ASID));
		System.out.println(getName() + " preInvokeRequester MVS_JOBID: " + data.getData(DataRequester.MVS_JOBID));
		System.out.println(getName() + " preInvokeRequester MVS_JOBNAME: " + data.getData(DataRequester.MVS_JOBNAME));
		System.out.println(getName() + " preInvokeRequester MVS_SID: " + data.getData(DataRequester.MVS_SID));
		System.out.println(getName() + " preInvokeRequester MVS_SYSNAME: " + data.getData(DataRequester.MVS_SYSNAME));
		
		System.out.println(getName() + " preInvokeRequester exit");
		
	}
	
	@Override
	public void postInvokeRequester(Map<Object, Object> requestStateMap, DataRequester data) throws InterceptorException {
		System.out.println(getName() + " postInvokeRequester entry");
		
		System.out.println(getName() + " postInvokeRequester API_REQUESTER_DESCRIPTION " + data.getData(DataRequester.API_REQUESTER_DESCRIPTION));
		System.out.println(getName() + " postInvokeRequester API_REQUESTER_NAME " + data.getData(DataRequester.API_REQUESTER_NAME));
		System.out.println(getName() + " postInvokeRequester API_REQUESTER_VERSION " + data.getData(DataRequester.API_REQUESTER_VERSION));
		System.out.println(getName() + " postInvokeRequester AUTHORIZATION_ADMIN_GROUP_NAMES " + data.getData(DataRequester.AUTHORIZATION_ADMIN_GROUP_NAMES));
		System.out.println(getName() + " postInvokeRequester AUTHORIZATION_INVOKE_GROUP_NAMES " + data.getData(DataRequester.AUTHORIZATION_INVOKE_GROUP_NAMES));
		System.out.println(getName() + " postInvokeRequester AUTHORIZATION_OPERATIONS_GROUP_NAMES " + data.getData(DataRequester.AUTHORIZATION_OPERATIONS_GROUP_NAMES));
		System.out.println(getName() + " postInvokeRequester AUTHORIZATION_READER_GROUP_NAMES " + data.getData(DataRequester.AUTHORIZATION_READER_GROUP_NAMES));
		System.out.println(getName() + " postInvokeRequester ENDPOINT_IDENTIFIER " + data.getData(DataRequester.ENDPOINT_IDENTIFIER));
		System.out.println(getName() + " postInvokeRequester ENDPOINT_METHOD " + data.getData(DataRequester.ENDPOINT_METHOD));
		System.out.println(getName() + " postInvokeRequester ENDPOINT_QUERY_STRING " + data.getData(DataRequester.ENDPOINT_QUERY_STRING));
		System.out.println(getName() + " postInvokeRequester ENDPOINT_REFERENCE " + data.getData(DataRequester.ENDPOINT_REFERENCE));
		System.out.println(getName() + " postInvokeRequester ENDPOINT_FULL_PATH " + data.getData(DataRequester.ENDPOINT_FULL_PATH));
		System.out.println(getName() + " postInvokeRequester HTTP_RESPONSE_CODE " + data.getData(DataRequester.HTTP_RESPONSE_CODE));
		System.out.println(getName() + " postInvokeRequester REQUEST_ID " + data.getData(DataRequester.REQUEST_ID));
		System.out.println(getName() + " postInvokeRequester TIME_ENDPOINT_RECEIVED " + data.getData(DataRequester.TIME_ENDPOINT_RECEIVED));
		System.out.println(getName() + " postInvokeRequester TIME_ENDPOINT_SENT " + data.getData(DataRequester.TIME_ENDPOINT_SENT));
		System.out.println(getName() + " postInvokeRequester TIME_ZOS_CONNECT_ENTRY " + data.getData(DataRequester.TIME_ZOS_CONNECT_ENTRY));
		System.out.println(getName() + " postInvokeRequester TIME_ZOS_CONNECT_EXIT " + data.getData(DataRequester.TIME_ZOS_CONNECT_EXIT));
		System.out.println(getName() + " postInvokeRequester USER_NAME_ASSERTED " + data.getData(DataRequester.USER_NAME_ASSERTED));
		System.out.println(getName() + " postInvokeRequester USER_PRINCIPAL " + data.getData(DataRequester.USER_PRINCIPAL));
		System.out.println(getName() + " postInvokeRequester CICS_APPLID: " + data.getData(DataRequester.CICS_APPLID));
		System.out.println(getName() + " postInvokeRequester CICS_TASK_NUMBER: " + data.getData(DataRequester.CICS_TASK_NUMBER));
		System.out.println(getName() + " postInvokeRequester CICS_TRANSID: " + data.getData(DataRequester.CICS_TRANSID));
		System.out.println(getName() + " postInvokeRequester IMS_APPNAME: " + data.getData(DataRequester.IMS_APPNAME));
		System.out.println(getName() + " postInvokeRequester IMS_IDENTIFIER: " + data.getData(DataRequester.IMS_IDENTIFIER));
		System.out.println(getName() + " postInvokeRequester IMS_REGION_ID: " + data.getData(DataRequester.IMS_REGION_ID));
		System.out.println(getName() + " postInvokeRequester IMS_TRANSNAME: " + data.getData(DataRequester.IMS_TRANSNAME));
		System.out.println(getName() + " postInvokeRequester MVS_ASID: " + data.getData(DataRequester.MVS_ASID));
		System.out.println(getName() + " postInvokeRequester MVS_JOBID: " + data.getData(DataRequester.MVS_JOBID));
		System.out.println(getName() + " postInvokeRequester MVS_JOBNAME: " + data.getData(DataRequester.MVS_JOBNAME));
		System.out.println(getName() + " postInvokeRequester MVS_SID: " + data.getData(DataRequester.MVS_SID));
		System.out.println(getName() + " postInvokeRequester MVS_SYSNAME: " + data.getData(DataRequester.MVS_SYSNAME));
		
		System.out.println(getName() + " postInvokeRequester exit");
		
	}

}
