package com.rpc.silkroad;

import java.io.Serializable;

/**
 * Created by frio on 16/6/24.
 */
public class RemoteCall implements Serializable {
    private static final long serialVersionUID = -8409101340140935127L;
    private String interfaceName;
    private String version;
    private String method;
    private Object[] args;

    private RemoteCall() {

    }

    public static RemoteCall createRemoteCall(String interfaceName, String method, Object[] args, String version) {
        RemoteCall remoteCall = new RemoteCall();
        remoteCall.setInterfaceName(interfaceName);
        remoteCall.setMethod(method);
        remoteCall.setArgs(args);
        remoteCall.setVersion(version);
        return remoteCall;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
