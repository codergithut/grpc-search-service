package grpc.search.oauth.o.grpc.service;

import grpc.search.oauth.o.grpc.model.OauthReply;
import grpc.search.oauth.o.grpc.model.OauthRequest;
import grpc.search.oauth.o.grpc.model.OauthServerProto;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * The greeting server definition.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.9.1)",
    comments = "Source: OauthService.proto")
public final class GetOauthTokenGrpc {

  private GetOauthTokenGrpc() {}

  public static final String SERVICE_NAME = "helloworld.GetOauthToken";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getGetOauthTokenMethod()} instead. 
  public static final io.grpc.MethodDescriptor<OauthRequest,
          OauthReply> METHOD_GET_OAUTH_TOKEN = getGetOauthTokenMethod();

  private static volatile io.grpc.MethodDescriptor<OauthRequest,
          OauthReply> getGetOauthTokenMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<OauthRequest,
          OauthReply> getGetOauthTokenMethod() {
    io.grpc.MethodDescriptor<OauthRequest, OauthReply> getGetOauthTokenMethod;
    if ((getGetOauthTokenMethod = GetOauthTokenGrpc.getGetOauthTokenMethod) == null) {
      synchronized (GetOauthTokenGrpc.class) {
        if ((getGetOauthTokenMethod = GetOauthTokenGrpc.getGetOauthTokenMethod) == null) {
          GetOauthTokenGrpc.getGetOauthTokenMethod = getGetOauthTokenMethod = 
              io.grpc.MethodDescriptor.<OauthRequest, OauthReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "helloworld.GetOauthToken", "getOauthToken"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  OauthRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  OauthReply.getDefaultInstance()))
                  .setSchemaDescriptor(new GetOauthTokenMethodDescriptorSupplier("getOauthToken"))
                  .build();
          }
        }
     }
     return getGetOauthTokenMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GetOauthTokenStub newStub(io.grpc.Channel channel) {
    return new GetOauthTokenStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GetOauthTokenBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new GetOauthTokenBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GetOauthTokenFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new GetOauthTokenFutureStub(channel);
  }

  /**
   * <pre>
   * The greeting server definition.
   * </pre>
   */
  public static abstract class GetOauthTokenImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public void getOauthToken(OauthRequest request,
        io.grpc.stub.StreamObserver<OauthReply> responseObserver) {
      asyncUnimplementedUnaryCall(getGetOauthTokenMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetOauthTokenMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                      OauthRequest,
                      OauthReply>(
                  this, METHODID_GET_OAUTH_TOKEN)))
          .build();
    }
  }

  /**
   * <pre>
   * The greeting server definition.
   * </pre>
   */
  public static final class GetOauthTokenStub extends io.grpc.stub.AbstractStub<GetOauthTokenStub> {
    private GetOauthTokenStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GetOauthTokenStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GetOauthTokenStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GetOauthTokenStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public void getOauthToken(OauthRequest request,
        io.grpc.stub.StreamObserver<OauthReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetOauthTokenMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * The greeting server definition.
   * </pre>
   */
  public static final class GetOauthTokenBlockingStub extends io.grpc.stub.AbstractStub<GetOauthTokenBlockingStub> {
    private GetOauthTokenBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GetOauthTokenBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GetOauthTokenBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GetOauthTokenBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public OauthReply getOauthToken(OauthRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetOauthTokenMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * The greeting server definition.
   * </pre>
   */
  public static final class GetOauthTokenFutureStub extends io.grpc.stub.AbstractStub<GetOauthTokenFutureStub> {
    private GetOauthTokenFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GetOauthTokenFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GetOauthTokenFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GetOauthTokenFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<OauthReply> getOauthToken(
        OauthRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetOauthTokenMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_OAUTH_TOKEN = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GetOauthTokenImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GetOauthTokenImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_OAUTH_TOKEN:
          serviceImpl.getOauthToken((OauthRequest) request,
              (io.grpc.stub.StreamObserver<OauthReply>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class GetOauthTokenBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GetOauthTokenBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return OauthServerProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GetOauthToken");
    }
  }

  private static final class GetOauthTokenFileDescriptorSupplier
      extends GetOauthTokenBaseDescriptorSupplier {
    GetOauthTokenFileDescriptorSupplier() {}
  }

  private static final class GetOauthTokenMethodDescriptorSupplier
      extends GetOauthTokenBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GetOauthTokenMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (GetOauthTokenGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GetOauthTokenFileDescriptorSupplier())
              .addMethod(getGetOauthTokenMethod())
              .build();
        }
      }
    }
    return result;
  }
}
