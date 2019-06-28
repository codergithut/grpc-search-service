package grpc.search.discovery.d.grpc.service;

import grpc.search.discovery.d.grpc.DiscoveryReply;
import grpc.search.discovery.d.grpc.DiscoveryRequest;
import grpc.search.discovery.d.grpc.DiscoveryServerProto;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
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
    comments = "Source: DiscoveryService.proto")
public final class GetDiscoveryGrpc {

  private GetDiscoveryGrpc() {}

  public static final String SERVICE_NAME = "helloworld.GetDiscovery";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getGetDiscoveryInfoMethod()} instead. 
  public static final io.grpc.MethodDescriptor<DiscoveryRequest,
          DiscoveryReply> METHOD_GET_DISCOVERY_INFO = getGetDiscoveryInfoMethod();

  private static volatile io.grpc.MethodDescriptor<DiscoveryRequest,
          DiscoveryReply> getGetDiscoveryInfoMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<DiscoveryRequest,
          DiscoveryReply> getGetDiscoveryInfoMethod() {
    io.grpc.MethodDescriptor<DiscoveryRequest, DiscoveryReply> getGetDiscoveryInfoMethod;
    if ((getGetDiscoveryInfoMethod = GetDiscoveryGrpc.getGetDiscoveryInfoMethod) == null) {
      synchronized (GetDiscoveryGrpc.class) {
        if ((getGetDiscoveryInfoMethod = GetDiscoveryGrpc.getGetDiscoveryInfoMethod) == null) {
          GetDiscoveryGrpc.getGetDiscoveryInfoMethod = getGetDiscoveryInfoMethod = 
              io.grpc.MethodDescriptor.<DiscoveryRequest, DiscoveryReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "helloworld.GetDiscovery", "getDiscoveryInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  DiscoveryRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  DiscoveryReply.getDefaultInstance()))
                  .setSchemaDescriptor(new GetDiscoveryMethodDescriptorSupplier("getDiscoveryInfo"))
                  .build();
          }
        }
     }
     return getGetDiscoveryInfoMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GetDiscoveryStub newStub(io.grpc.Channel channel) {
    return new GetDiscoveryStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GetDiscoveryBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new GetDiscoveryBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GetDiscoveryFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new GetDiscoveryFutureStub(channel);
  }

  /**
   * <pre>
   * The greeting server definition.
   * </pre>
   */
  public static abstract class GetDiscoveryImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public void getDiscoveryInfo(DiscoveryRequest request,
        io.grpc.stub.StreamObserver<DiscoveryReply> responseObserver) {
      asyncUnimplementedUnaryCall(getGetDiscoveryInfoMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetDiscoveryInfoMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                      DiscoveryRequest,
                      DiscoveryReply>(
                  this, METHODID_GET_DISCOVERY_INFO)))
          .build();
    }
  }

  /**
   * <pre>
   * The greeting server definition.
   * </pre>
   */
  public static final class GetDiscoveryStub extends io.grpc.stub.AbstractStub<GetDiscoveryStub> {
    private GetDiscoveryStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GetDiscoveryStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GetDiscoveryStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GetDiscoveryStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public void getDiscoveryInfo(DiscoveryRequest request,
        io.grpc.stub.StreamObserver<DiscoveryReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetDiscoveryInfoMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * The greeting server definition.
   * </pre>
   */
  public static final class GetDiscoveryBlockingStub extends io.grpc.stub.AbstractStub<GetDiscoveryBlockingStub> {
    private GetDiscoveryBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GetDiscoveryBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GetDiscoveryBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GetDiscoveryBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public DiscoveryReply getDiscoveryInfo(DiscoveryRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetDiscoveryInfoMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * The greeting server definition.
   * </pre>
   */
  public static final class GetDiscoveryFutureStub extends io.grpc.stub.AbstractStub<GetDiscoveryFutureStub> {
    private GetDiscoveryFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GetDiscoveryFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GetDiscoveryFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GetDiscoveryFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<DiscoveryReply> getDiscoveryInfo(
        DiscoveryRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetDiscoveryInfoMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_DISCOVERY_INFO = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GetDiscoveryImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GetDiscoveryImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_DISCOVERY_INFO:
          serviceImpl.getDiscoveryInfo((DiscoveryRequest) request,
              (io.grpc.stub.StreamObserver<DiscoveryReply>) responseObserver);
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

  private static abstract class GetDiscoveryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GetDiscoveryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return DiscoveryServerProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GetDiscovery");
    }
  }

  private static final class GetDiscoveryFileDescriptorSupplier
      extends GetDiscoveryBaseDescriptorSupplier {
    GetDiscoveryFileDescriptorSupplier() {}
  }

  private static final class GetDiscoveryMethodDescriptorSupplier
      extends GetDiscoveryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GetDiscoveryMethodDescriptorSupplier(String methodName) {
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
      synchronized (GetDiscoveryGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GetDiscoveryFileDescriptorSupplier())
              .addMethod(getGetDiscoveryInfoMethod())
              .build();
        }
      }
    }
    return result;
  }
}
