<?php
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: member-info.proto

use Google\Protobuf\Internal\GPBType;
use Google\Protobuf\Internal\RepeatedField;
use Google\Protobuf\Internal\GPBUtil;

/**
 * Generated from protobuf message <code>MemberInfoResponse</code>
 */
class MemberInfoResponse extends \Google\Protobuf\Internal\Message
{
    /**
     * Generated from protobuf field <code>int64 id = 1;</code>
     */
    protected $id = 0;
    /**
     * Generated from protobuf field <code>string email = 2;</code>
     */
    protected $email = '';
    /**
     * Generated from protobuf field <code>string identification = 3;</code>
     */
    protected $identification = '';
    /**
     * Generated from protobuf field <code>string phone_number = 4;</code>
     */
    protected $phone_number = '';

    /**
     * Constructor.
     *
     * @param array $data {
     *     Optional. Data for populating the Message object.
     *
     *     @type int|string $id
     *     @type string $email
     *     @type string $identification
     *     @type string $phone_number
     * }
     */
    public function __construct($data = NULL) {
        \GPBMetadata\MemberInfo::initOnce();
        parent::__construct($data);
    }

    /**
     * Generated from protobuf field <code>int64 id = 1;</code>
     * @return int|string
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Generated from protobuf field <code>int64 id = 1;</code>
     * @param int|string $var
     * @return $this
     */
    public function setId($var)
    {
        GPBUtil::checkInt64($var);
        $this->id = $var;

        return $this;
    }

    /**
     * Generated from protobuf field <code>string email = 2;</code>
     * @return string
     */
    public function getEmail()
    {
        return $this->email;
    }

    /**
     * Generated from protobuf field <code>string email = 2;</code>
     * @param string $var
     * @return $this
     */
    public function setEmail($var)
    {
        GPBUtil::checkString($var, True);
        $this->email = $var;

        return $this;
    }

    /**
     * Generated from protobuf field <code>string identification = 3;</code>
     * @return string
     */
    public function getIdentification()
    {
        return $this->identification;
    }

    /**
     * Generated from protobuf field <code>string identification = 3;</code>
     * @param string $var
     * @return $this
     */
    public function setIdentification($var)
    {
        GPBUtil::checkString($var, True);
        $this->identification = $var;

        return $this;
    }

    /**
     * Generated from protobuf field <code>string phone_number = 4;</code>
     * @return string
     */
    public function getPhoneNumber()
    {
        return $this->phone_number;
    }

    /**
     * Generated from protobuf field <code>string phone_number = 4;</code>
     * @param string $var
     * @return $this
     */
    public function setPhoneNumber($var)
    {
        GPBUtil::checkString($var, True);
        $this->phone_number = $var;

        return $this;
    }

}

