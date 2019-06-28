package grpc.search.discovery.d.grpc.service;

import grpc.search.discovery.d.grpc.DiscoveryServerProto;
import grpc.search.discovery.d.grpc.RegisterReply;
import grpc.search.discovery.d.grpc.RegisterRequest;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.9.1)",
    comments = "Source: DiscoveryService.proto")
public final class RegisterDiscoveryInfoGrpc {

  private RegisterDiscoveryInfoGrpc() {}

  public static final String SERVICE_NAME = "helloworld.RegisterDiscoveryInfo";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getRegisterDiscoverInfoMethod()} instead. 
  public static final io.grpc.MethodDescriptor<RegisterRequest,
          RegisterReply> METHOD_REGISTER_DISCOVER_INFO = getRegisterDiscoverInfoMethod();

  private static volatile io.grpc.MethodDescriptor<RegisterRequest,
          RegisterReply> getRegisterDiscoverInfoMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<RegisterRequest,
          RegisterReply> getRegisterDiscoverInfoMethod() {
    io.grpc.MethodDescriptor<RegisterRequest, RegisterReply> getRegisterDiscoverInfoMethod;
    if ((getRegisterDiscoverInfoMethod = RegisterDiscoveryInfoGrpc.getRegisterDiscoverInfoMethod) == null) {
      synchronized (RegisterDiscoveryInfoGrpc.class) {
        if ((getRegisterDiscoverInfoMethod = RegisterDiscoveryInfoGrpc.getRegisterDiscoverInfoMethod) == null) {
          RegisterDiscoveryInfoGrpc.getRegisterDiscoverInfoMethod = getRegisterDiscoverInfoMethod = 
              io.grpc.MethodDescriptor.<RegisterRequest, RegisterReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "helloworld.RegisterDiscoveryInfo", "registerDiscoverInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  RegisterRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  RegisterReply.getDefaultInstance()))
                  .setSchemaDescriptor(new RegisterDiscoveryInfoMethodDescriptorSupplier("registerDiscoverInfo"))
                  .build();
          }
        }
     }
     return getRegisterDiscoverInfoMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RegisterDiscoveryInfoStub newStub(io.grpc.Channel channel) {
    return new RegisterDiscoveryInfoStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RegisterDiscoveryInfoBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new RegisterDiscoveryInfoBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static RegisterDiscoveryInfoFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new RegisterDiscoveryInfoFutureStub(channel);
  }

  /**
   */
  public static abstract class RegisterDiscoveryInfoImplBase implements io.grpc.BindableService {

    /**
     */
    public void registerDiscoverInfo(RegisterRequest request,
        io.grpc.stub.StreamObserver<RegisterReply> responseObserver) {
      asyncUnimplementedUnaryCall(getRegisterDiscoverInfoMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRegisterDiscoverInfoMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                      RegisterRequest,
                      RegisterReply>(
                  this, METHODID_REGISTER_DISCOVER_INFO)))
          .build();
    }
  }

  /**
   */
  public static final class RegisterDiscoveryInfoStub extends io.grpc.stub.AbstractStub<RegisterDiscoveryInfoStub> {
    private RegisterDiscoveryInfoStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RegisterDiscoveryInfoStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RegisterDiscoveryInfoStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RegisterDiscoveryInfoStub(channel, callOptions);
    }

    /**
     */
    public void registerDiscoverInfo(RegisterRequest request,
        io.grpc.stub.StreamObserver<RegisterReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRegisterDiscoverInfoMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class RegisterDiscoveryInfoBlockingStub extends io.grpc.stub.AbstractStub<RegisterDiscoveryInfoBlockingStub> {
    private RegisterDiscoveryInfoBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RegisterDiscoveryInfoBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RegisterDiscoveryInfoBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RegisterDiscoveryInfoBlockingStub(channel, callOptions);
    }

    /**
     */
    public RegisterReply registerDiscoverInfo(RegisterRequest request) {
      return blockingUnaryCall(
          getChannel(), getRegisterDiscoverInfoMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class RegisterDiscoveryInfoFutureStub extends io.grpc.stub.AbstractStub<RegisterDiscoveryInfoFutureStub> {
    private RegisterDiscoveryInfoFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RegisterDiscoveryInfoFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RegisterDiscoveryInfoFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RegisterDiscoveryInfoFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<RegisterReply> registerDiscoverInfo(
        RegisterRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRegisterDiscoverInfoMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REGISTER_DISCOVER_INFO = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final RegisterDiscoveryInfoImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(RegisterDiscoveryInfoImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REGISTER_DISCOVER_INFO:
          serviceImpl.registerDiscoverInfo((RegisterRequest) request,
              (io.grpc.stub.StreamObserver<RegisterReply>) responseObserver);
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

  private static abstract class RegisterDiscoveryInfoBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    RegisterDiscoveryInfoBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return DiscoveryServerProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("RegisterDiscoveryInfo");
    }
  }

  private static final class RegisterDiscoveryInfoFileDescriptorSupplier
      extends RegisterDiscoveryInfoBaseDescriptorSupplier {
    RegisterDiscoveryInfoFileDescriptorSupplier() {}
  }

  private static final class RegisterDiscoveryInfoMethodDescriptorSupplier
      extends RegisterDiscoveryInfoBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    RegisterDiscoveryInfoMethodDescriptorSupplier(String methodName) {
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
      synchronized (RegisterDiscoveryInfoGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RegisterDiscoveryInfoFileDescriptorSupplier())
              .addMethod(getRegisterDiscoverInfoMethod())
              .build();
        }
      }
    }
    return result;
  }
}
