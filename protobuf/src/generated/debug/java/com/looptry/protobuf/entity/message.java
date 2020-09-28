// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: mssage.proto

package com.looptry.protobuf.entity;

public final class message {
  private message() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }
  public interface UserInfoOrBuilder extends
      // @@protoc_insertion_point(interface_extends:com.looptry.protobuf.entity.UserInfo)
      com.google.protobuf.MessageLiteOrBuilder {

    /**
     * <code>string userName = 1;</code>
     */
    java.lang.String getUserName();
    /**
     * <code>string userName = 1;</code>
     */
    com.google.protobuf.ByteString
        getUserNameBytes();
  }
  /**
   * Protobuf type {@code com.looptry.protobuf.entity.UserInfo}
   */
  public  static final class UserInfo extends
      com.google.protobuf.GeneratedMessageLite<
          UserInfo, UserInfo.Builder> implements
      // @@protoc_insertion_point(message_implements:com.looptry.protobuf.entity.UserInfo)
      UserInfoOrBuilder {
    private UserInfo() {
      userName_ = "";
    }
    public static final int USERNAME_FIELD_NUMBER = 1;
    private java.lang.String userName_;
    /**
     * <code>string userName = 1;</code>
     */
    @java.lang.Override
    public java.lang.String getUserName() {
      return userName_;
    }
    /**
     * <code>string userName = 1;</code>
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getUserNameBytes() {
      return com.google.protobuf.ByteString.copyFromUtf8(userName_);
    }
    /**
     * <code>string userName = 1;</code>
     */
    private void setUserName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      userName_ = value;
    }
    /**
     * <code>string userName = 1;</code>
     */
    private void clearUserName() {
      
      userName_ = getDefaultInstance().getUserName();
    }
    /**
     * <code>string userName = 1;</code>
     */
    private void setUserNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      userName_ = value.toStringUtf8();
    }

    public static com.looptry.protobuf.entity.message.UserInfo parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data);
    }
    public static com.looptry.protobuf.entity.message.UserInfo parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data, extensionRegistry);
    }
    public static com.looptry.protobuf.entity.message.UserInfo parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data);
    }
    public static com.looptry.protobuf.entity.message.UserInfo parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data, extensionRegistry);
    }
    public static com.looptry.protobuf.entity.message.UserInfo parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data);
    }
    public static com.looptry.protobuf.entity.message.UserInfo parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data, extensionRegistry);
    }
    public static com.looptry.protobuf.entity.message.UserInfo parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, input);
    }
    public static com.looptry.protobuf.entity.message.UserInfo parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, input, extensionRegistry);
    }
    public static com.looptry.protobuf.entity.message.UserInfo parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }
    public static com.looptry.protobuf.entity.message.UserInfo parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }
    public static com.looptry.protobuf.entity.message.UserInfo parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, input);
    }
    public static com.looptry.protobuf.entity.message.UserInfo parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
      return (Builder) DEFAULT_INSTANCE.createBuilder();
    }
    public static Builder newBuilder(com.looptry.protobuf.entity.message.UserInfo prototype) {
      return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code com.looptry.protobuf.entity.UserInfo}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageLite.Builder<
          com.looptry.protobuf.entity.message.UserInfo, Builder> implements
        // @@protoc_insertion_point(builder_implements:com.looptry.protobuf.entity.UserInfo)
        com.looptry.protobuf.entity.message.UserInfoOrBuilder {
      // Construct using com.looptry.protobuf.entity.message.UserInfo.newBuilder()
      private Builder() {
        super(DEFAULT_INSTANCE);
      }


      /**
       * <code>string userName = 1;</code>
       */
      @java.lang.Override
      public java.lang.String getUserName() {
        return instance.getUserName();
      }
      /**
       * <code>string userName = 1;</code>
       */
      @java.lang.Override
      public com.google.protobuf.ByteString
          getUserNameBytes() {
        return instance.getUserNameBytes();
      }
      /**
       * <code>string userName = 1;</code>
       */
      public Builder setUserName(
          java.lang.String value) {
        copyOnWrite();
        instance.setUserName(value);
        return this;
      }
      /**
       * <code>string userName = 1;</code>
       */
      public Builder clearUserName() {
        copyOnWrite();
        instance.clearUserName();
        return this;
      }
      /**
       * <code>string userName = 1;</code>
       */
      public Builder setUserNameBytes(
          com.google.protobuf.ByteString value) {
        copyOnWrite();
        instance.setUserNameBytes(value);
        return this;
      }

      // @@protoc_insertion_point(builder_scope:com.looptry.protobuf.entity.UserInfo)
    }
    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected final java.lang.Object dynamicMethod(
        com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
        java.lang.Object arg0, java.lang.Object arg1) {
      switch (method) {
        case NEW_MUTABLE_INSTANCE: {
          return new com.looptry.protobuf.entity.message.UserInfo();
        }
        case NEW_BUILDER: {
          return new Builder();
        }
        case BUILD_MESSAGE_INFO: {
            java.lang.Object[] objects = new java.lang.Object[] {
              "userName_",
            };
            java.lang.String info =
                "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u0208";
            return newMessageInfo(DEFAULT_INSTANCE, info, objects);
        }
        // fall through
        case GET_DEFAULT_INSTANCE: {
          return DEFAULT_INSTANCE;
        }
        case GET_PARSER: {
          com.google.protobuf.Parser<com.looptry.protobuf.entity.message.UserInfo> parser = PARSER;
          if (parser == null) {
            synchronized (com.looptry.protobuf.entity.message.UserInfo.class) {
              parser = PARSER;
              if (parser == null) {
                parser = new DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                PARSER = parser;
              }
            }
          }
          return parser;
      }
      case GET_MEMOIZED_IS_INITIALIZED: {
        return (byte) 1;
      }
      case SET_MEMOIZED_IS_INITIALIZED: {
        return null;
      }
      }
      throw new UnsupportedOperationException();
    }


    // @@protoc_insertion_point(class_scope:com.looptry.protobuf.entity.UserInfo)
    private static final com.looptry.protobuf.entity.message.UserInfo DEFAULT_INSTANCE;
    static {
      // New instances are implicitly immutable so no need to make
      // immutable.
      DEFAULT_INSTANCE = new UserInfo();
    }

    static {
      com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(
        UserInfo.class, DEFAULT_INSTANCE);
    }
    public static com.looptry.protobuf.entity.message.UserInfo getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<UserInfo> PARSER;

    public static com.google.protobuf.Parser<UserInfo> parser() {
      return DEFAULT_INSTANCE.getParserForType();
    }
  }


  static {
  }

  // @@protoc_insertion_point(outer_class_scope)
}
