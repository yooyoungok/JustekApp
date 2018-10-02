//
// �� ������ JAXB(JavaTM Architecture for XML Binding) ���� ���� 2.2.8-b130911.1802 ������ ���� �����Ǿ����ϴ�. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>�� �����Ͻʽÿ�. 
// �� ������ �����ϸ� �ҽ� ��Ű���� ���������� �� ���� ������ �սǵ˴ϴ�. 
// ���� ��¥: 2018.05.23 �ð� 02:09:01 PM KST 
//


package justek.ide.model.xmlv2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "vendor",
    "descriptions"
})
@XmlRootElement(name = "EtherCATInfo")
public class EtherCATInfoV2old {

    @XmlElement(name = "Vendor")
    protected EtherCATInfoV2old.Vendor vendor;
    @XmlElement(name = "Descriptions")
    protected EtherCATInfoV2old.Descriptions descriptions;
    @XmlAttribute(name = "Version")
    protected BigDecimal version;

    /**
     * vendor �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link EtherCATInfoV2old.Vendor }
     *     
     */
    public EtherCATInfoV2old.Vendor getVendor() {
        return vendor;
    }

    /**
     * vendor �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link EtherCATInfoV2old.Vendor }
     *     
     */
    public void setVendor(EtherCATInfoV2old.Vendor value) {
        this.vendor = value;
    }

    /**
     * descriptions �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link EtherCATInfoV2old.Descriptions }
     *     
     */
    public EtherCATInfoV2old.Descriptions getDescriptions() {
        return descriptions;
    }

    /**
     * descriptions �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link EtherCATInfoV2old.Descriptions }
     *     
     */
    public void setDescriptions(EtherCATInfoV2old.Descriptions value) {
        this.descriptions = value;
    }

    /**
     * version �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVersion() {
        return version;
    }

    /**
     * version �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVersion(BigDecimal value) {
        this.version = value;
    }


    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "groups",
        "devices"
    })
    public static class Descriptions {

        @XmlElement(name = "Groups")
        protected EtherCATInfoV2old.Descriptions.Groups groups;
        @XmlElement(name = "Devices")
        protected EtherCATInfoV2old.Descriptions.Devices devices;

        /**
         * groups �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link EtherCATInfoV2old.Descriptions.Groups }
         *     
         */
        public EtherCATInfoV2old.Descriptions.Groups getGroups() {
            return groups;
        }

        /**
         * groups �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link EtherCATInfoV2old.Descriptions.Groups }
         *     
         */
        public void setGroups(EtherCATInfoV2old.Descriptions.Groups value) {
            this.groups = value;
        }

        /**
         * devices �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link EtherCATInfoV2old.Descriptions.Devices }
         *     
         */
        public EtherCATInfoV2old.Descriptions.Devices getDevices() {
            return devices;
        }

        /**
         * devices �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link EtherCATInfoV2old.Descriptions.Devices }
         *     
         */
        public void setDevices(EtherCATInfoV2old.Descriptions.Devices value) {
            this.devices = value;
        }


        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "device"
        })
        public static class Devices {

            @XmlElement(name = "Device")
            protected EtherCATInfoV2old.Descriptions.Devices.Device device;

            /**
             * device �Ӽ��� ���� �����ɴϴ�.
             * 
             * @return
             *     possible object is
             *     {@link EtherCATInfoV2old.Descriptions.Devices.Device }
             *     
             */
            public EtherCATInfoV2old.Descriptions.Devices.Device getDevice() {
                return device;
            }

            /**
             * device �Ӽ��� ���� �����մϴ�.
             * 
             * @param value
             *     allowed object is
             *     {@link EtherCATInfoV2old.Descriptions.Devices.Device }
             *     
             */
            public void setDevice(EtherCATInfoV2old.Descriptions.Devices.Device value) {
                this.device = value;
            }


            /**
             *                   &lt;element name="ByteSize" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" minOccurs="0"/>
             *                   &lt;element name="ConfigData" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *                 &lt;/sequence>
             *               &lt;/restriction>
             *             &lt;/complexContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *         &lt;element name="ImageData16x14" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *       &lt;/sequence>
             *       &lt;attribute name="Physics" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "type",
                "name",
                "info",
                "groupType",
                "profile",
                "fmmu",
                "sm",
                "rxPdo",
                "txPdo",
                "mailbox",
                "dc",
                "eeprom",
                "imageData16X14"
            })
            public static class Device {

                @XmlElement(name = "Type")
                protected EtherCATInfoV2old.Descriptions.Devices.Device.Type type;
                @XmlElement(name = "Name")
                protected List<EtherCATInfoV2old.Descriptions.Devices.Device.Name> name;
                @XmlElement(name = "Info")
                protected EtherCATInfoV2old.Descriptions.Devices.Device.Info info;
                @XmlElement(name = "GroupType")
                protected String groupType;
                @XmlElement(name = "Profile")
                protected EtherCATInfoV2old.Descriptions.Devices.Device.Profile profile;
                @XmlElement(name = "Fmmu")
                protected List<String> fmmu;
                @XmlElement(name = "Sm")
                protected List<EtherCATInfoV2old.Descriptions.Devices.Device.Sm> sm;
                @XmlElement(name = "RxPdo")
                protected EtherCATInfoV2old.Descriptions.Devices.Device.RxPdo rxPdo;
                @XmlElement(name = "TxPdo")
                protected EtherCATInfoV2old.Descriptions.Devices.Device.TxPdo txPdo;
                @XmlElement(name = "Mailbox")
                protected EtherCATInfoV2old.Descriptions.Devices.Device.Mailbox mailbox;
                @XmlElement(name = "Dc")
                protected EtherCATInfoV2old.Descriptions.Devices.Device.Dc dc;
                @XmlElement(name = "Eeprom")
                protected EtherCATInfoV2old.Descriptions.Devices.Device.Eeprom eeprom;
                @XmlElement(name = "ImageData16x14")
                protected String imageData16X14;
                @XmlAttribute(name = "Physics")
                protected String physics;

                /**
                 * type �Ӽ��� ���� �����ɴϴ�.
                 * 
                 * @return
                 *     possible object is
                 *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.Type }
                 *     
                 */
                public EtherCATInfoV2old.Descriptions.Devices.Device.Type getType() {
                    return type;
                }

                /**
                 * type �Ӽ��� ���� �����մϴ�.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.Type }
                 *     
                 */
                public void setType(EtherCATInfoV2old.Descriptions.Devices.Device.Type value) {
                    this.type = value;
                }

                /**
                 * Gets the value of the name property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the name property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getName().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link EtherCATInfoV2old.Descriptions.Devices.Device.Name }
                 * 
                 * 
                 */
                public List<EtherCATInfoV2old.Descriptions.Devices.Device.Name> getName() {
                    if (name == null) {
                        name = new ArrayList<EtherCATInfoV2old.Descriptions.Devices.Device.Name>();
                    }
                    return this.name;
                }

                /**
                 * info �Ӽ��� ���� �����ɴϴ�.
                 * 
                 * @return
                 *     possible object is
                 *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.Info }
                 *     
                 */
                public EtherCATInfoV2old.Descriptions.Devices.Device.Info getInfo() {
                    return info;
                }

                /**
                 * info �Ӽ��� ���� �����մϴ�.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.Info }
                 *     
                 */
                public void setInfo(EtherCATInfoV2old.Descriptions.Devices.Device.Info value) {
                    this.info = value;
                }

                /**
                 * groupType �Ӽ��� ���� �����ɴϴ�.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getGroupType() {
                    return groupType;
                }

                /**
                 * groupType �Ӽ��� ���� �����մϴ�.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setGroupType(String value) {
                    this.groupType = value;
                }

                /**
                 * profile �Ӽ��� ���� �����ɴϴ�.
                 * 
                 * @return
                 *     possible object is
                 *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.Profile }
                 *     
                 */
                public EtherCATInfoV2old.Descriptions.Devices.Device.Profile getProfile() {
                    return profile;
                }

                /**
                 * profile �Ӽ��� ���� �����մϴ�.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.Profile }
                 *     
                 */
                public void setProfile(EtherCATInfoV2old.Descriptions.Devices.Device.Profile value) {
                    this.profile = value;
                }

                /**
                 * Gets the value of the fmmu property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the fmmu property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getFmmu().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link String }
                 * 
                 * 
                 */
                public List<String> getFmmu() {
                    if (fmmu == null) {
                        fmmu = new ArrayList<String>();
                    }
                    return this.fmmu;
                }

                /**
                 * Gets the value of the sm property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the sm property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getSm().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link EtherCATInfoV2old.Descriptions.Devices.Device.Sm }
                 * 
                 * 
                 */
                public List<EtherCATInfoV2old.Descriptions.Devices.Device.Sm> getSm() {
                    if (sm == null) {
                        sm = new ArrayList<EtherCATInfoV2old.Descriptions.Devices.Device.Sm>();
                    }
                    return this.sm;
                }

                /**
                 * rxPdo �Ӽ��� ���� �����ɴϴ�.
                 * 
                 * @return
                 *     possible object is
                 *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.RxPdo }
                 *     
                 */
                public EtherCATInfoV2old.Descriptions.Devices.Device.RxPdo getRxPdo() {
                    return rxPdo;
                }

                /**
                 * rxPdo �Ӽ��� ���� �����մϴ�.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.RxPdo }
                 *     
                 */
                public void setRxPdo(EtherCATInfoV2old.Descriptions.Devices.Device.RxPdo value) {
                    this.rxPdo = value;
                }

                /**
                 * txPdo �Ӽ��� ���� �����ɴϴ�.
                 * 
                 * @return
                 *     possible object is
                 *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.TxPdo }
                 *     
                 */
                public EtherCATInfoV2old.Descriptions.Devices.Device.TxPdo getTxPdo() {
                    return txPdo;
                }

                /**
                 * txPdo �Ӽ��� ���� �����մϴ�.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.TxPdo }
                 *     
                 */
                public void setTxPdo(EtherCATInfoV2old.Descriptions.Devices.Device.TxPdo value) {
                    this.txPdo = value;
                }

                /**
                 * mailbox �Ӽ��� ���� �����ɴϴ�.
                 * 
                 * @return
                 *     possible object is
                 *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.Mailbox }
                 *     
                 */
                public EtherCATInfoV2old.Descriptions.Devices.Device.Mailbox getMailbox() {
                    return mailbox;
                }

                /**
                 * mailbox �Ӽ��� ���� �����մϴ�.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.Mailbox }
                 *     
                 */
                public void setMailbox(EtherCATInfoV2old.Descriptions.Devices.Device.Mailbox value) {
                    this.mailbox = value;
                }

                /**
                 * dc �Ӽ��� ���� �����ɴϴ�.
                 * 
                 * @return
                 *     possible object is
                 *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.Dc }
                 *     
                 */
                public EtherCATInfoV2old.Descriptions.Devices.Device.Dc getDc() {
                    return dc;
                }

                /**
                 * dc �Ӽ��� ���� �����մϴ�.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.Dc }
                 *     
                 */
                public void setDc(EtherCATInfoV2old.Descriptions.Devices.Device.Dc value) {
                    this.dc = value;
                }

                /**
                 * eeprom �Ӽ��� ���� �����ɴϴ�.
                 * 
                 * @return
                 *     possible object is
                 *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.Eeprom }
                 *     
                 */
                public EtherCATInfoV2old.Descriptions.Devices.Device.Eeprom getEeprom() {
                    return eeprom;
                }

                /**
                 * eeprom �Ӽ��� ���� �����մϴ�.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.Eeprom }
                 *     
                 */
                public void setEeprom(EtherCATInfoV2old.Descriptions.Devices.Device.Eeprom value) {
                    this.eeprom = value;
                }

                /**
                 * imageData16X14 �Ӽ��� ���� �����ɴϴ�.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getImageData16X14() {
                    return imageData16X14;
                }

                /**
                 * imageData16X14 �Ӽ��� ���� �����մϴ�.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setImageData16X14(String value) {
                    this.imageData16X14 = value;
                }

                /**
                 * physics �Ӽ��� ���� �����ɴϴ�.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getPhysics() {
                    return physics;
                }

                /**
                 * physics �Ӽ��� ���� �����մϴ�.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setPhysics(String value) {
                    this.physics = value;
                }


                /**
                 * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
                 * 
                 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
                 * 
                 * <pre>
                 * &lt;complexType>
                 *   &lt;complexContent>
                 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *       &lt;sequence>
                 *         &lt;element name="OpMode" maxOccurs="unbounded" minOccurs="0">
                 *           &lt;complexType>
                 *             &lt;complexContent>
                 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                 &lt;sequence>
                 *                   &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                 *                   &lt;element name="Desc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                 *                   &lt;element name="AssignActivate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                 *                   &lt;element name="CycleTimeSync0" minOccurs="0">
                 *                     &lt;complexType>
                 *                       &lt;simpleContent>
                 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>unsignedByte">
                 *                           &lt;attribute name="Factor" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" />
                 *                         &lt;/extension>
                 *                       &lt;/simpleContent>
                 *                     &lt;/complexType>
                 *                   &lt;/element>
                 *                   &lt;element name="ShiftTimeSync0" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/>
                 *                   &lt;element name="CycleTimeSync1" minOccurs="0">
                 *                     &lt;complexType>
                 *                       &lt;simpleContent>
                 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>unsignedByte">
                 *                           &lt;attribute name="Factor" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" />
                 *                         &lt;/extension>
                 *                       &lt;/simpleContent>
                 *                     &lt;/complexType>
                 *                   &lt;/element>
                 *                 &lt;/sequence>
                 *               &lt;/restriction>
                 *             &lt;/complexContent>
                 *           &lt;/complexType>
                 *         &lt;/element>
                 *       &lt;/sequence>
                 *     &lt;/restriction>
                 *   &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 * 
                 * 
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                    "opMode"
                })
                public static class Dc {

                    @XmlElement(name = "OpMode")
                    protected List<EtherCATInfoV2old.Descriptions.Devices.Device.Dc.OpMode> opMode;

                    /**
                     * Gets the value of the opMode property.
                     * 
                     * <p>
                     * This accessor method returns a reference to the live list,
                     * not a snapshot. Therefore any modification you make to the
                     * returned list will be present inside the JAXB object.
                     * This is why there is not a <CODE>set</CODE> method for the opMode property.
                     * 
                     * <p>
                     * For example, to add a new item, do as follows:
                     * <pre>
                     *    getOpMode().add(newItem);
                     * </pre>
                     * 
                     * 
                     * <p>
                     * Objects of the following type(s) are allowed in the list
                     * {@link EtherCATInfoV2old.Descriptions.Devices.Device.Dc.OpMode }
                     * 
                     * 
                     */
                    public List<EtherCATInfoV2old.Descriptions.Devices.Device.Dc.OpMode> getOpMode() {
                        if (opMode == null) {
                            opMode = new ArrayList<EtherCATInfoV2old.Descriptions.Devices.Device.Dc.OpMode>();
                        }
                        return this.opMode;
                    }


                    /**
                     * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
                     * 
                     * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
                     * 
                     * <pre>
                     * &lt;complexType>
                     *   &lt;complexContent>
                     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                     *       &lt;sequence>
                     *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                     *         &lt;element name="Desc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                     *         &lt;element name="AssignActivate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                     *         &lt;element name="CycleTimeSync0" minOccurs="0">
                     *           &lt;complexType>
                     *             &lt;simpleContent>
                     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>unsignedByte">
                     *                 &lt;attribute name="Factor" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" />
                     *               &lt;/extension>
                     *             &lt;/simpleContent>
                     *           &lt;/complexType>
                     *         &lt;/element>
                     *         &lt;element name="ShiftTimeSync0" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/>
                     *         &lt;element name="CycleTimeSync1" minOccurs="0">
                     *           &lt;complexType>
                     *             &lt;simpleContent>
                     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>unsignedByte">
                     *                 &lt;attribute name="Factor" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" />
                     *               &lt;/extension>
                     *             &lt;/simpleContent>
                     *           &lt;/complexType>
                     *         &lt;/element>
                     *       &lt;/sequence>
                     *     &lt;/restriction>
                     *   &lt;/complexContent>
                     * &lt;/complexType>
                     * </pre>
                     * 
                     * 
                     */
                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "", propOrder = {
                        "name",
                        "desc",
                        "assignActivate",
                        "cycleTimeSync0",
                        "shiftTimeSync0",
                        "cycleTimeSync1"
                    })
                    public static class OpMode {

                        @XmlElement(name = "Name")
                        protected String name;
                        @XmlElement(name = "Desc")
                        protected String desc;
                        @XmlElement(name = "AssignActivate")
                        protected String assignActivate;
                        @XmlElement(name = "CycleTimeSync0")
                        protected EtherCATInfoV2old.Descriptions.Devices.Device.Dc.OpMode.CycleTimeSync0 cycleTimeSync0;
                        @XmlElement(name = "ShiftTimeSync0")
                        @XmlSchemaType(name = "unsignedByte")
                        protected Short shiftTimeSync0;
                        @XmlElement(name = "CycleTimeSync1")
                        protected EtherCATInfoV2old.Descriptions.Devices.Device.Dc.OpMode.CycleTimeSync1 cycleTimeSync1;

                        /**
                         * name �Ӽ��� ���� �����ɴϴ�.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getName() {
                            return name;
                        }

                        /**
                         * name �Ӽ��� ���� �����մϴ�.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setName(String value) {
                            this.name = value;
                        }

                        /**
                         * desc �Ӽ��� ���� �����ɴϴ�.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getDesc() {
                            return desc;
                        }

                        /**
                         * desc �Ӽ��� ���� �����մϴ�.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setDesc(String value) {
                            this.desc = value;
                        }

                        /**
                         * assignActivate �Ӽ��� ���� �����ɴϴ�.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getAssignActivate() {
                            return assignActivate;
                        }

                        /**
                         * assignActivate �Ӽ��� ���� �����մϴ�.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setAssignActivate(String value) {
                            this.assignActivate = value;
                        }

                        /**
                         * cycleTimeSync0 �Ӽ��� ���� �����ɴϴ�.
                         * 
                         * @return
                         *     possible object is
                         *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.Dc.OpMode.CycleTimeSync0 }
                         *     
                         */
                        public EtherCATInfoV2old.Descriptions.Devices.Device.Dc.OpMode.CycleTimeSync0 getCycleTimeSync0() {
                            return cycleTimeSync0;
                        }

                        /**
                         * cycleTimeSync0 �Ӽ��� ���� �����մϴ�.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.Dc.OpMode.CycleTimeSync0 }
                         *     
                         */
                        public void setCycleTimeSync0(EtherCATInfoV2old.Descriptions.Devices.Device.Dc.OpMode.CycleTimeSync0 value) {
                            this.cycleTimeSync0 = value;
                        }

                        /**
                         * shiftTimeSync0 �Ӽ��� ���� �����ɴϴ�.
                         * 
                         * @return
                         *     possible object is
                         *     {@link Short }
                         *     
                         */
                        public Short getShiftTimeSync0() {
                            return shiftTimeSync0;
                        }

                        /**
                         * shiftTimeSync0 �Ӽ��� ���� �����մϴ�.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link Short }
                         *     
                         */
                        public void setShiftTimeSync0(Short value) {
                            this.shiftTimeSync0 = value;
                        }

                        /**
                         * cycleTimeSync1 �Ӽ��� ���� �����ɴϴ�.
                         * 
                         * @return
                         *     possible object is
                         *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.Dc.OpMode.CycleTimeSync1 }
                         *     
                         */
                        public EtherCATInfoV2old.Descriptions.Devices.Device.Dc.OpMode.CycleTimeSync1 getCycleTimeSync1() {
                            return cycleTimeSync1;
                        }

                        /**
                         * cycleTimeSync1 �Ӽ��� ���� �����մϴ�.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.Dc.OpMode.CycleTimeSync1 }
                         *     
                         */
                        public void setCycleTimeSync1(EtherCATInfoV2old.Descriptions.Devices.Device.Dc.OpMode.CycleTimeSync1 value) {
                            this.cycleTimeSync1 = value;
                        }


                        /**
                         * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
                         * 
                         * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
                         * 
                         * <pre>
                         * &lt;complexType>
                         *   &lt;simpleContent>
                         *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>unsignedByte">
                         *       &lt;attribute name="Factor" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" />
                         *     &lt;/extension>
                         *   &lt;/simpleContent>
                         * &lt;/complexType>
                         * </pre>
                         * 
                         * 
                         */
                        @XmlAccessorType(XmlAccessType.FIELD)
                        @XmlType(name = "", propOrder = {
                            "value"
                        })
                        public static class CycleTimeSync0 {

                            @XmlValue
                            @XmlSchemaType(name = "unsignedByte")
                            protected short value;
                            @XmlAttribute(name = "Factor")
                            @XmlSchemaType(name = "unsignedByte")
                            protected Short factor;

                            /**
                             * value �Ӽ��� ���� �����ɴϴ�.
                             * 
                             */
                            public short getValue() {
                                return value;
                            }

                            /**
                             * value �Ӽ��� ���� �����մϴ�.
                             * 
                             */
                            public void setValue(short value) {
                                this.value = value;
                            }

                            /**
                             * factor �Ӽ��� ���� �����ɴϴ�.
                             * 
                             * @return
                             *     possible object is
                             *     {@link Short }
                             *     
                             */
                            public Short getFactor() {
                                return factor;
                            }

                            /**
                             * factor �Ӽ��� ���� �����մϴ�.
                             * 
                             * @param value
                             *     allowed object is
                             *     {@link Short }
                             *     
                             */
                            public void setFactor(Short value) {
                                this.factor = value;
                            }

                        }


                        /**
                         * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
                         * 
                         * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
                         * 
                         * <pre>
                         * &lt;complexType>
                         *   &lt;simpleContent>
                         *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>unsignedByte">
                         *       &lt;attribute name="Factor" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" />
                         *     &lt;/extension>
                         *   &lt;/simpleContent>
                         * &lt;/complexType>
                         * </pre>
                         * 
                         * 
                         */
                        @XmlAccessorType(XmlAccessType.FIELD)
                        @XmlType(name = "", propOrder = {
                            "value"
                        })
                        public static class CycleTimeSync1 {

                            @XmlValue
                            @XmlSchemaType(name = "unsignedByte")
                            protected short value;
                            @XmlAttribute(name = "Factor")
                            @XmlSchemaType(name = "unsignedByte")
                            protected Short factor;

                            /**
                             * value �Ӽ��� ���� �����ɴϴ�.
                             * 
                             */
                            public short getValue() {
                                return value;
                            }

                            /**
                             * value �Ӽ��� ���� �����մϴ�.
                             * 
                             */
                            public void setValue(short value) {
                                this.value = value;
                            }

                            /**
                             * factor �Ӽ��� ���� �����ɴϴ�.
                             * 
                             * @return
                             *     possible object is
                             *     {@link Short }
                             *     
                             */
                            public Short getFactor() {
                                return factor;
                            }

                            /**
                             * factor �Ӽ��� ���� �����մϴ�.
                             * 
                             * @param value
                             *     allowed object is
                             *     {@link Short }
                             *     
                             */
                            public void setFactor(Short value) {
                                this.factor = value;
                            }

                        }

                    }

                }


                /**
                 * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
                 * 
                 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
                 * 
                 * <pre>
                 * &lt;complexType>
                 *   &lt;complexContent>
                 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *       &lt;sequence>
                 *         &lt;element name="ByteSize" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" minOccurs="0"/>
                 *         &lt;element name="ConfigData" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                 *       &lt;/sequence>
                 *     &lt;/restriction>
                 *   &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 * 
                 * 
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                    "byteSize",
                    "configData"
                })
                public static class Eeprom {

                    @XmlElement(name = "ByteSize")
                    @XmlSchemaType(name = "unsignedShort")
                    protected Integer byteSize;
                    @XmlElement(name = "ConfigData")
                    protected String configData;

                    /**
                     * byteSize �Ӽ��� ���� �����ɴϴ�.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Integer }
                     *     
                     */
                    public Integer getByteSize() {
                        return byteSize;
                    }

                    /**
                     * byteSize �Ӽ��� ���� �����մϴ�.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Integer }
                     *     
                     */
                    public void setByteSize(Integer value) {
                        this.byteSize = value;
                    }

                    /**
                     * configData �Ӽ��� ���� �����ɴϴ�.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getConfigData() {
                        return configData;
                    }

                    /**
                     * configData �Ӽ��� ���� �����մϴ�.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setConfigData(String value) {
                        this.configData = value;
                    }

                }


                /**
                 * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
                 * 
                 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
                 * 
                 * <pre>
                 * &lt;complexType>
                 *   &lt;complexContent>
                 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *       &lt;sequence>
                 *         &lt;element name="StateMachine" minOccurs="0">
                 *           &lt;complexType>
                 *             &lt;complexContent>
                 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                 &lt;sequence>
                 *                   &lt;element name="Timeout" minOccurs="0">
                 *                     &lt;complexType>
                 *                       &lt;complexContent>
                 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                           &lt;sequence>
                 *                             &lt;element name="PreopTimeout" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" minOccurs="0"/>
                 *                             &lt;element name="SafeopOpTimeout" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" minOccurs="0"/>
                 *                             &lt;element name="BackToInitTimeout" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" minOccurs="0"/>
                 *                             &lt;element name="BackToSafeopTimeout" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/>
                 *                           &lt;/sequence>
                 *                         &lt;/restriction>
                 *                       &lt;/complexContent>
                 *                     &lt;/complexType>
                 *                   &lt;/element>
                 *                 &lt;/sequence>
                 *               &lt;/restriction>
                 *             &lt;/complexContent>
                 *           &lt;/complexType>
                 *         &lt;/element>
                 *         &lt;element name="Mailbox" minOccurs="0">
                 *           &lt;complexType>
                 *             &lt;complexContent>
                 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                 &lt;sequence>
                 *                   &lt;element name="Timeout" minOccurs="0">
                 *                     &lt;complexType>
                 *                       &lt;complexContent>
                 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                           &lt;sequence>
                 *                             &lt;element name="RequestTimeout" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/>
                 *                             &lt;element name="ResponseTimeout" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" minOccurs="0"/>
                 *                           &lt;/sequence>
                 *                         &lt;/restriction>
                 *                       &lt;/complexContent>
                 *                     &lt;/complexType>
                 *                   &lt;/element>
                 *                 &lt;/sequence>
                 *               &lt;/restriction>
                 *             &lt;/complexContent>
                 *           &lt;/complexType>
                 *         &lt;/element>
                 *       &lt;/sequence>
                 *     &lt;/restriction>
                 *   &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 * 
                 * 
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                    "stateMachine",
                    "mailbox"
                })
                public static class Info {

                    @XmlElement(name = "StateMachine")
                    protected EtherCATInfoV2old.Descriptions.Devices.Device.Info.StateMachine stateMachine;
                    @XmlElement(name = "Mailbox")
                    protected EtherCATInfoV2old.Descriptions.Devices.Device.Info.Mailbox mailbox;

                    /**
                     * stateMachine �Ӽ��� ���� �����ɴϴ�.
                     * 
                     * @return
                     *     possible object is
                     *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.Info.StateMachine }
                     *     
                     */
                    public EtherCATInfoV2old.Descriptions.Devices.Device.Info.StateMachine getStateMachine() {
                        return stateMachine;
                    }

                    /**
                     * stateMachine �Ӽ��� ���� �����մϴ�.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.Info.StateMachine }
                     *     
                     */
                    public void setStateMachine(EtherCATInfoV2old.Descriptions.Devices.Device.Info.StateMachine value) {
                        this.stateMachine = value;
                    }

                    /**
                     * mailbox �Ӽ��� ���� �����ɴϴ�.
                     * 
                     * @return
                     *     possible object is
                     *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.Info.Mailbox }
                     *     
                     */
                    public EtherCATInfoV2old.Descriptions.Devices.Device.Info.Mailbox getMailbox() {
                        return mailbox;
                    }

                    /**
                     * mailbox �Ӽ��� ���� �����մϴ�.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.Info.Mailbox }
                     *     
                     */
                    public void setMailbox(EtherCATInfoV2old.Descriptions.Devices.Device.Info.Mailbox value) {
                        this.mailbox = value;
                    }


                    /**
                     * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
                     * 
                     * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
                     * 
                     * <pre>
                     * &lt;complexType>
                     *   &lt;complexContent>
                     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                     *       &lt;sequence>
                     *         &lt;element name="Timeout" minOccurs="0">
                     *           &lt;complexType>
                     *             &lt;complexContent>
                     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                     *                 &lt;sequence>
                     *                   &lt;element name="RequestTimeout" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/>
                     *                   &lt;element name="ResponseTimeout" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" minOccurs="0"/>
                     *                 &lt;/sequence>
                     *               &lt;/restriction>
                     *             &lt;/complexContent>
                     *           &lt;/complexType>
                     *         &lt;/element>
                     *       &lt;/sequence>
                     *     &lt;/restriction>
                     *   &lt;/complexContent>
                     * &lt;/complexType>
                     * </pre>
                     * 
                     * 
                     */
                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "", propOrder = {
                        "timeout"
                    })
                    public static class Mailbox {

                        @XmlElement(name = "Timeout")
                        protected EtherCATInfoV2old.Descriptions.Devices.Device.Info.Mailbox.Timeout timeout;

                        /**
                         * timeout �Ӽ��� ���� �����ɴϴ�.
                         * 
                         * @return
                         *     possible object is
                         *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.Info.Mailbox.Timeout }
                         *     
                         */
                        public EtherCATInfoV2old.Descriptions.Devices.Device.Info.Mailbox.Timeout getTimeout() {
                            return timeout;
                        }

                        /**
                         * timeout �Ӽ��� ���� �����մϴ�.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.Info.Mailbox.Timeout }
                         *     
                         */
                        public void setTimeout(EtherCATInfoV2old.Descriptions.Devices.Device.Info.Mailbox.Timeout value) {
                            this.timeout = value;
                        }


                        /**
                         * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
                         * 
                         * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
                         * 
                         * <pre>
                         * &lt;complexType>
                         *   &lt;complexContent>
                         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                         *       &lt;sequence>
                         *         &lt;element name="RequestTimeout" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/>
                         *         &lt;element name="ResponseTimeout" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" minOccurs="0"/>
                         *       &lt;/sequence>
                         *     &lt;/restriction>
                         *   &lt;/complexContent>
                         * &lt;/complexType>
                         * </pre>
                         * 
                         * 
                         */
                        @XmlAccessorType(XmlAccessType.FIELD)
                        @XmlType(name = "", propOrder = {
                            "requestTimeout",
                            "responseTimeout"
                        })
                        public static class Timeout {

                            @XmlElement(name = "RequestTimeout")
                            @XmlSchemaType(name = "unsignedByte")
                            protected Short requestTimeout;
                            @XmlElement(name = "ResponseTimeout")
                            @XmlSchemaType(name = "unsignedShort")
                            protected Integer responseTimeout;

                            /**
                             * requestTimeout �Ӽ��� ���� �����ɴϴ�.
                             * 
                             * @return
                             *     possible object is
                             *     {@link Short }
                             *     
                             */
                            public Short getRequestTimeout() {
                                return requestTimeout;
                            }

                            /**
                             * requestTimeout �Ӽ��� ���� �����մϴ�.
                             * 
                             * @param value
                             *     allowed object is
                             *     {@link Short }
                             *     
                             */
                            public void setRequestTimeout(Short value) {
                                this.requestTimeout = value;
                            }

                            /**
                             * responseTimeout �Ӽ��� ���� �����ɴϴ�.
                             * 
                             * @return
                             *     possible object is
                             *     {@link Integer }
                             *     
                             */
                            public Integer getResponseTimeout() {
                                return responseTimeout;
                            }

                            /**
                             * responseTimeout �Ӽ��� ���� �����մϴ�.
                             * 
                             * @param value
                             *     allowed object is
                             *     {@link Integer }
                             *     
                             */
                            public void setResponseTimeout(Integer value) {
                                this.responseTimeout = value;
                            }

                        }

                    }


                    /**
                     * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
                     * 
                     * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
                     * 
                     * <pre>
                     * &lt;complexType>
                     *   &lt;complexContent>
                     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                     *       &lt;sequence>
                     *         &lt;element name="Timeout" minOccurs="0">
                     *           &lt;complexType>
                     *             &lt;complexContent>
                     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                     *                 &lt;sequence>
                     *                   &lt;element name="PreopTimeout" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" minOccurs="0"/>
                     *                   &lt;element name="SafeopOpTimeout" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" minOccurs="0"/>
                     *                   &lt;element name="BackToInitTimeout" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" minOccurs="0"/>
                     *                   &lt;element name="BackToSafeopTimeout" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/>
                     *                 &lt;/sequence>
                     *               &lt;/restriction>
                     *             &lt;/complexContent>
                     *           &lt;/complexType>
                     *         &lt;/element>
                     *       &lt;/sequence>
                     *     &lt;/restriction>
                     *   &lt;/complexContent>
                     * &lt;/complexType>
                     * </pre>
                     * 
                     * 
                     */
                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "", propOrder = {
                        "timeout"
                    })
                    public static class StateMachine {

                        @XmlElement(name = "Timeout")
                        protected EtherCATInfoV2old.Descriptions.Devices.Device.Info.StateMachine.Timeout timeout;

                        /**
                         * timeout �Ӽ��� ���� �����ɴϴ�.
                         * 
                         * @return
                         *     possible object is
                         *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.Info.StateMachine.Timeout }
                         *     
                         */
                        public EtherCATInfoV2old.Descriptions.Devices.Device.Info.StateMachine.Timeout getTimeout() {
                            return timeout;
                        }

                        /**
                         * timeout �Ӽ��� ���� �����մϴ�.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.Info.StateMachine.Timeout }
                         *     
                         */
                        public void setTimeout(EtherCATInfoV2old.Descriptions.Devices.Device.Info.StateMachine.Timeout value) {
                            this.timeout = value;
                        }


                        /**
                         * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
                         * 
                         * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
                         * 
                         * <pre>
                         * &lt;complexType>
                         *   &lt;complexContent>
                         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                         *       &lt;sequence>
                         *         &lt;element name="PreopTimeout" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" minOccurs="0"/>
                         *         &lt;element name="SafeopOpTimeout" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" minOccurs="0"/>
                         *         &lt;element name="BackToInitTimeout" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" minOccurs="0"/>
                         *         &lt;element name="BackToSafeopTimeout" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/>
                         *       &lt;/sequence>
                         *     &lt;/restriction>
                         *   &lt;/complexContent>
                         * &lt;/complexType>
                         * </pre>
                         * 
                         * 
                         */
                        @XmlAccessorType(XmlAccessType.FIELD)
                        @XmlType(name = "", propOrder = {
                            "preopTimeout",
                            "safeopOpTimeout",
                            "backToInitTimeout",
                            "backToSafeopTimeout"
                        })
                        public static class Timeout {

                            @XmlElement(name = "PreopTimeout")
                            @XmlSchemaType(name = "unsignedShort")
                            protected Integer preopTimeout;
                            @XmlElement(name = "SafeopOpTimeout")
                            @XmlSchemaType(name = "unsignedShort")
                            protected Integer safeopOpTimeout;
                            @XmlElement(name = "BackToInitTimeout")
                            @XmlSchemaType(name = "unsignedShort")
                            protected Integer backToInitTimeout;
                            @XmlElement(name = "BackToSafeopTimeout")
                            @XmlSchemaType(name = "unsignedByte")
                            protected Short backToSafeopTimeout;

                            /**
                             * preopTimeout �Ӽ��� ���� �����ɴϴ�.
                             * 
                             * @return
                             *     possible object is
                             *     {@link Integer }
                             *     
                             */
                            public Integer getPreopTimeout() {
                                return preopTimeout;
                            }

                            /**
                             * preopTimeout �Ӽ��� ���� �����մϴ�.
                             * 
                             * @param value
                             *     allowed object is
                             *     {@link Integer }
                             *     
                             */
                            public void setPreopTimeout(Integer value) {
                                this.preopTimeout = value;
                            }

                            /**
                             * safeopOpTimeout �Ӽ��� ���� �����ɴϴ�.
                             * 
                             * @return
                             *     possible object is
                             *     {@link Integer }
                             *     
                             */
                            public Integer getSafeopOpTimeout() {
                                return safeopOpTimeout;
                            }

                            /**
                             * safeopOpTimeout �Ӽ��� ���� �����մϴ�.
                             * 
                             * @param value
                             *     allowed object is
                             *     {@link Integer }
                             *     
                             */
                            public void setSafeopOpTimeout(Integer value) {
                                this.safeopOpTimeout = value;
                            }

                            /**
                             * backToInitTimeout �Ӽ��� ���� �����ɴϴ�.
                             * 
                             * @return
                             *     possible object is
                             *     {@link Integer }
                             *     
                             */
                            public Integer getBackToInitTimeout() {
                                return backToInitTimeout;
                            }

                            /**
                             * backToInitTimeout �Ӽ��� ���� �����մϴ�.
                             * 
                             * @param value
                             *     allowed object is
                             *     {@link Integer }
                             *     
                             */
                            public void setBackToInitTimeout(Integer value) {
                                this.backToInitTimeout = value;
                            }

                            /**
                             * backToSafeopTimeout �Ӽ��� ���� �����ɴϴ�.
                             * 
                             * @return
                             *     possible object is
                             *     {@link Short }
                             *     
                             */
                            public Short getBackToSafeopTimeout() {
                                return backToSafeopTimeout;
                            }

                            /**
                             * backToSafeopTimeout �Ӽ��� ���� �����մϴ�.
                             * 
                             * @param value
                             *     allowed object is
                             *     {@link Short }
                             *     
                             */
                            public void setBackToSafeopTimeout(Short value) {
                                this.backToSafeopTimeout = value;
                            }

                        }

                    }

                }


                /**
                 * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
                 * 
                 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
                 * 
                 * <pre>
                 * &lt;complexType>
                 *   &lt;complexContent>
                 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *       &lt;sequence>
                 *         &lt;element name="EoE" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
                 *         &lt;element name="CoE" minOccurs="0">
                 *           &lt;complexType>
                 *             &lt;complexContent>
                 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                 &lt;attribute name="SdoInfo" type="{http://www.w3.org/2001/XMLSchema}boolean" />
                 *                 &lt;attribute name="SegmentedSdo" type="{http://www.w3.org/2001/XMLSchema}boolean" />
                 *                 &lt;attribute name="CompleteAccess" type="{http://www.w3.org/2001/XMLSchema}boolean" />
                 *               &lt;/restriction>
                 *             &lt;/complexContent>
                 *           &lt;/complexType>
                 *         &lt;/element>
                 *         &lt;element name="FoE" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
                 *       &lt;/sequence>
                 *       &lt;attribute name="DataLinkLayer" type="{http://www.w3.org/2001/XMLSchema}boolean" />
                 *     &lt;/restriction>
                 *   &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 * 
                 * 
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                    "eoE",
                    "coE",
                    "foE"
                })
                public static class Mailbox {

                    @XmlElement(name = "EoE")
                    protected java.lang.Object eoE;
                    @XmlElement(name = "CoE")
                    protected EtherCATInfoV2old.Descriptions.Devices.Device.Mailbox.CoE coE;
                    @XmlElement(name = "FoE")
                    protected java.lang.Object foE;
                    @XmlAttribute(name = "DataLinkLayer")
                    protected Boolean dataLinkLayer;

                    /**
                     * eoE �Ӽ��� ���� �����ɴϴ�.
                     * 
                     * @return
                     *     possible object is
                     *     {@link java.lang.Object }
                     *     
                     */
                    public java.lang.Object getEoE() {
                        return eoE;
                    }

                    /**
                     * eoE �Ӽ��� ���� �����մϴ�.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link java.lang.Object }
                     *     
                     */
                    public void setEoE(java.lang.Object value) {
                        this.eoE = value;
                    }

                    /**
                     * coE �Ӽ��� ���� �����ɴϴ�.
                     * 
                     * @return
                     *     possible object is
                     *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.Mailbox.CoE }
                     *     
                     */
                    public EtherCATInfoV2old.Descriptions.Devices.Device.Mailbox.CoE getCoE() {
                        return coE;
                    }

                    /**
                     * coE �Ӽ��� ���� �����մϴ�.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.Mailbox.CoE }
                     *     
                     */
                    public void setCoE(EtherCATInfoV2old.Descriptions.Devices.Device.Mailbox.CoE value) {
                        this.coE = value;
                    }

                    /**
                     * foE �Ӽ��� ���� �����ɴϴ�.
                     * 
                     * @return
                     *     possible object is
                     *     {@link java.lang.Object }
                     *     
                     */
                    public java.lang.Object getFoE() {
                        return foE;
                    }

                    /**
                     * foE �Ӽ��� ���� �����մϴ�.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link java.lang.Object }
                     *     
                     */
                    public void setFoE(java.lang.Object value) {
                        this.foE = value;
                    }

                    /**
                     * dataLinkLayer �Ӽ��� ���� �����ɴϴ�.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Boolean }
                     *     
                     */
                    public Boolean isDataLinkLayer() {
                        return dataLinkLayer;
                    }

                    /**
                     * dataLinkLayer �Ӽ��� ���� �����մϴ�.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Boolean }
                     *     
                     */
                    public void setDataLinkLayer(Boolean value) {
                        this.dataLinkLayer = value;
                    }


                    /**
                     * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
                     * 
                     * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
                     * 
                     * <pre>
                     * &lt;complexType>
                     *   &lt;complexContent>
                     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                     *       &lt;attribute name="SdoInfo" type="{http://www.w3.org/2001/XMLSchema}boolean" />
                     *       &lt;attribute name="SegmentedSdo" type="{http://www.w3.org/2001/XMLSchema}boolean" />
                     *       &lt;attribute name="CompleteAccess" type="{http://www.w3.org/2001/XMLSchema}boolean" />
                     *     &lt;/restriction>
                     *   &lt;/complexContent>
                     * &lt;/complexType>
                     * </pre>
                     * 
                     * 
                     */
                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "")
                    public static class CoE {

                        @XmlAttribute(name = "SdoInfo")
                        protected Boolean sdoInfo;
                        @XmlAttribute(name = "SegmentedSdo")
                        protected Boolean segmentedSdo;
                        @XmlAttribute(name = "CompleteAccess")
                        protected Boolean completeAccess;

                        /**
                         * sdoInfo �Ӽ��� ���� �����ɴϴ�.
                         * 
                         * @return
                         *     possible object is
                         *     {@link Boolean }
                         *     
                         */
                        public Boolean isSdoInfo() {
                            return sdoInfo;
                        }

                        /**
                         * sdoInfo �Ӽ��� ���� �����մϴ�.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link Boolean }
                         *     
                         */
                        public void setSdoInfo(Boolean value) {
                            this.sdoInfo = value;
                        }

                        /**
                         * segmentedSdo �Ӽ��� ���� �����ɴϴ�.
                         * 
                         * @return
                         *     possible object is
                         *     {@link Boolean }
                         *     
                         */
                        public Boolean isSegmentedSdo() {
                            return segmentedSdo;
                        }

                        /**
                         * segmentedSdo �Ӽ��� ���� �����մϴ�.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link Boolean }
                         *     
                         */
                        public void setSegmentedSdo(Boolean value) {
                            this.segmentedSdo = value;
                        }

                        /**
                         * completeAccess �Ӽ��� ���� �����ɴϴ�.
                         * 
                         * @return
                         *     possible object is
                         *     {@link Boolean }
                         *     
                         */
                        public Boolean isCompleteAccess() {
                            return completeAccess;
                        }

                        /**
                         * completeAccess �Ӽ��� ���� �����մϴ�.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link Boolean }
                         *     
                         */
                        public void setCompleteAccess(Boolean value) {
                            this.completeAccess = value;
                        }

                    }

                }


                /**
                 * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
                 * 
                 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
                 * 
                 * <pre>
                 * &lt;complexType>
                 *   &lt;simpleContent>
                 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
                 *       &lt;attribute name="LcId" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" />
                 *     &lt;/extension>
                 *   &lt;/simpleContent>
                 * &lt;/complexType>
                 * </pre>
                 * 
                 * 
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                    "value"
                })
                public static class Name {

                    @XmlValue
                    protected String value;
                    @XmlAttribute(name = "LcId")
                    @XmlSchemaType(name = "unsignedShort")
                    protected Integer lcId;

                    /**
                     * value �Ӽ��� ���� �����ɴϴ�.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getValue() {
                        return value;
                    }

                    /**
                     * value �Ӽ��� ���� �����մϴ�.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setValue(String value) {
                        this.value = value;
                    }

                    /**
                     * lcId �Ӽ��� ���� �����ɴϴ�.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Integer }
                     *     
                     */
                    public Integer getLcId() {
                        return lcId;
                    }

                    /**
                     * lcId �Ӽ��� ���� �����մϴ�.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Integer }
                     *     
                     */
                    public void setLcId(Integer value) {
                        this.lcId = value;
                    }

                }


                /**
                 * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
                 * 
                 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
                 * 
                 * <pre>
                 * &lt;complexType>
                 *   &lt;complexContent>
                 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *       &lt;sequence>
                 *         &lt;element name="ChannelInfo" minOccurs="0">
                 *           &lt;complexType>
                 *             &lt;complexContent>
                 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                 &lt;sequence>
                 *                   &lt;element name="ProfileNo" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" minOccurs="0"/>
                 *                 &lt;/sequence>
                 *               &lt;/restriction>
                 *             &lt;/complexContent>
                 *           &lt;/complexType>
                 *         &lt;/element>
                 *         &lt;element name="Dictionary" minOccurs="0">
                 *           &lt;complexType>
                 *             &lt;complexContent>
                 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                 &lt;sequence>
                 *                   &lt;element name="DataTypes" minOccurs="0">
                 *                     &lt;complexType>
                 *                       &lt;complexContent>
                 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                           &lt;sequence>
                 *                             &lt;element name="DataType" minOccurs="0">
                 *                               &lt;complexType>
                 *                                 &lt;complexContent>
                 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                                     &lt;sequence>
                 *                                       &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                 *                                       &lt;element name="BitSize" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/>
                 *                                     &lt;/sequence>
                 *                                   &lt;/restriction>
                 *                                 &lt;/complexContent>
                 *                               &lt;/complexType>
                 *                             &lt;/element>
                 *                           &lt;/sequence>
                 *                         &lt;/restriction>
                 *                       &lt;/complexContent>
                 *                     &lt;/complexType>
                 *                   &lt;/element>
                 *                   &lt;element name="Objects" minOccurs="0">
                 *                     &lt;complexType>
                 *                       &lt;complexContent>
                 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                           &lt;sequence>
                 *                             &lt;element name="Object" minOccurs="0">
                 *                               &lt;complexType>
                 *                                 &lt;complexContent>
                 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                                     &lt;sequence>
                 *                                       &lt;element name="Index" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                 *                                       &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                 *                                       &lt;element name="Type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                 *                                       &lt;element name="BitSize" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/>
                 *                                       &lt;element name="Info" minOccurs="0">
                 *                                         &lt;complexType>
                 *                                           &lt;complexContent>
                 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                                               &lt;sequence>
                 *                                                 &lt;element name="DefaultData" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/>
                 *                                               &lt;/sequence>
                 *                                             &lt;/restriction>
                 *                                           &lt;/complexContent>
                 *                                         &lt;/complexType>
                 *                                       &lt;/element>
                 *                                       &lt;element name="Flags" minOccurs="0">
                 *                                         &lt;complexType>
                 *                                           &lt;complexContent>
                 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                                               &lt;sequence>
                 *                                                 &lt;element name="Access" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                 *                                                 &lt;element name="Category" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                 *                                                 &lt;element name="PdoMapping" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                 *                                               &lt;/sequence>
                 *                                             &lt;/restriction>
                 *                                           &lt;/complexContent>
                 *                                         &lt;/complexType>
                 *                                       &lt;/element>
                 *                                     &lt;/sequence>
                 *                                   &lt;/restriction>
                 *                                 &lt;/complexContent>
                 *                               &lt;/complexType>
                 *                             &lt;/element>
                 *                           &lt;/sequence>
                 *                         &lt;/restriction>
                 *                       &lt;/complexContent>
                 *                     &lt;/complexType>
                 *                   &lt;/element>
                 *                 &lt;/sequence>
                 *               &lt;/restriction>
                 *             &lt;/complexContent>
                 *           &lt;/complexType>
                 *         &lt;/element>
                 *       &lt;/sequence>
                 *     &lt;/restriction>
                 *   &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 * 
                 * 
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                    "channelInfo",
                    "dictionary"
                })
                public static class Profile {

                    @XmlElement(name = "ChannelInfo")
                    protected EtherCATInfoV2old.Descriptions.Devices.Device.Profile.ChannelInfo channelInfo;
                    @XmlElement(name = "Dictionary")
                    protected EtherCATInfoV2old.Descriptions.Devices.Device.Profile.Dictionary dictionary;

                    /**
                     * channelInfo �Ӽ��� ���� �����ɴϴ�.
                     * 
                     * @return
                     *     possible object is
                     *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.Profile.ChannelInfo }
                     *     
                     */
                    public EtherCATInfoV2old.Descriptions.Devices.Device.Profile.ChannelInfo getChannelInfo() {
                        return channelInfo;
                    }

                    /**
                     * channelInfo �Ӽ��� ���� �����մϴ�.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.Profile.ChannelInfo }
                     *     
                     */
                    public void setChannelInfo(EtherCATInfoV2old.Descriptions.Devices.Device.Profile.ChannelInfo value) {
                        this.channelInfo = value;
                    }

                    /**
                     * dictionary �Ӽ��� ���� �����ɴϴ�.
                     * 
                     * @return
                     *     possible object is
                     *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.Profile.Dictionary }
                     *     
                     */
                    public EtherCATInfoV2old.Descriptions.Devices.Device.Profile.Dictionary getDictionary() {
                        return dictionary;
                    }

                    /**
                     * dictionary �Ӽ��� ���� �����մϴ�.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.Profile.Dictionary }
                     *     
                     */
                    public void setDictionary(EtherCATInfoV2old.Descriptions.Devices.Device.Profile.Dictionary value) {
                        this.dictionary = value;
                    }


                    /**
                     * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
                     * 
                     * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
                     * 
                     * <pre>
                     * &lt;complexType>
                     *   &lt;complexContent>
                     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                     *       &lt;sequence>
                     *         &lt;element name="ProfileNo" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" minOccurs="0"/>
                     *       &lt;/sequence>
                     *     &lt;/restriction>
                     *   &lt;/complexContent>
                     * &lt;/complexType>
                     * </pre>
                     * 
                     * 
                     */
                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "", propOrder = {
                        "profileNo"
                    })
                    public static class ChannelInfo {

                        @XmlElement(name = "ProfileNo")
                        @XmlSchemaType(name = "unsignedShort")
                        protected Integer profileNo;

                        /**
                         * profileNo �Ӽ��� ���� �����ɴϴ�.
                         * 
                         * @return
                         *     possible object is
                         *     {@link Integer }
                         *     
                         */
                        public Integer getProfileNo() {
                            return profileNo;
                        }

                        /**
                         * profileNo �Ӽ��� ���� �����մϴ�.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link Integer }
                         *     
                         */
                        public void setProfileNo(Integer value) {
                            this.profileNo = value;
                        }

                    }


                    /**
                     * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
                     * 
                     * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
                     * 
                     * <pre>
                     * &lt;complexType>
                     *   &lt;complexContent>
                     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                     *       &lt;sequence>
                     *         &lt;element name="DataTypes" minOccurs="0">
                     *           &lt;complexType>
                     *             &lt;complexContent>
                     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                     *                 &lt;sequence>
                     *                   &lt;element name="DataType" minOccurs="0">
                     *                     &lt;complexType>
                     *                       &lt;complexContent>
                     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                     *                           &lt;sequence>
                     *                             &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                     *                             &lt;element name="BitSize" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/>
                     *                           &lt;/sequence>
                     *                         &lt;/restriction>
                     *                       &lt;/complexContent>
                     *                     &lt;/complexType>
                     *                   &lt;/element>
                     *                 &lt;/sequence>
                     *               &lt;/restriction>
                     *             &lt;/complexContent>
                     *           &lt;/complexType>
                     *         &lt;/element>
                     *         &lt;element name="Objects" minOccurs="0">
                     *           &lt;complexType>
                     *             &lt;complexContent>
                     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                     *                 &lt;sequence>
                     *                   &lt;element name="Object" minOccurs="0">
                     *                     &lt;complexType>
                     *                       &lt;complexContent>
                     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                     *                           &lt;sequence>
                     *                             &lt;element name="Index" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                     *                             &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                     *                             &lt;element name="Type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                     *                             &lt;element name="BitSize" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/>
                     *                             &lt;element name="Info" minOccurs="0">
                     *                               &lt;complexType>
                     *                                 &lt;complexContent>
                     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                     *                                     &lt;sequence>
                     *                                       &lt;element name="DefaultData" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/>
                     *                                     &lt;/sequence>
                     *                                   &lt;/restriction>
                     *                                 &lt;/complexContent>
                     *                               &lt;/complexType>
                     *                             &lt;/element>
                     *                             &lt;element name="Flags" minOccurs="0">
                     *                               &lt;complexType>
                     *                                 &lt;complexContent>
                     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                     *                                     &lt;sequence>
                     *                                       &lt;element name="Access" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                     *                                       &lt;element name="Category" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                     *                                       &lt;element name="PdoMapping" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                     *                                     &lt;/sequence>
                     *                                   &lt;/restriction>
                     *                                 &lt;/complexContent>
                     *                               &lt;/complexType>
                     *                             &lt;/element>
                     *                           &lt;/sequence>
                     *                         &lt;/restriction>
                     *                       &lt;/complexContent>
                     *                     &lt;/complexType>
                     *                   &lt;/element>
                     *                 &lt;/sequence>
                     *               &lt;/restriction>
                     *             &lt;/complexContent>
                     *           &lt;/complexType>
                     *         &lt;/element>
                     *       &lt;/sequence>
                     *     &lt;/restriction>
                     *   &lt;/complexContent>
                     * &lt;/complexType>
                     * </pre>
                     * 
                     * 
                     */
                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "", propOrder = {
                        "dataTypes",
                        "objects"
                    })
                    public static class Dictionary {

                        @XmlElement(name = "DataTypes")
                        protected EtherCATInfoV2old.Descriptions.Devices.Device.Profile.Dictionary.DataTypes dataTypes;
                        @XmlElement(name = "Objects")
                        protected EtherCATInfoV2old.Descriptions.Devices.Device.Profile.Dictionary.Objects objects;

                        /**
                         * dataTypes �Ӽ��� ���� �����ɴϴ�.
                         * 
                         * @return
                         *     possible object is
                         *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.Profile.Dictionary.DataTypes }
                         *     
                         */
                        public EtherCATInfoV2old.Descriptions.Devices.Device.Profile.Dictionary.DataTypes getDataTypes() {
                            return dataTypes;
                        }

                        /**
                         * dataTypes �Ӽ��� ���� �����մϴ�.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.Profile.Dictionary.DataTypes }
                         *     
                         */
                        public void setDataTypes(EtherCATInfoV2old.Descriptions.Devices.Device.Profile.Dictionary.DataTypes value) {
                            this.dataTypes = value;
                        }

                        /**
                         * objects �Ӽ��� ���� �����ɴϴ�.
                         * 
                         * @return
                         *     possible object is
                         *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.Profile.Dictionary.Objects }
                         *     
                         */
                        public EtherCATInfoV2old.Descriptions.Devices.Device.Profile.Dictionary.Objects getObjects() {
                            return objects;
                        }

                        /**
                         * objects �Ӽ��� ���� �����մϴ�.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.Profile.Dictionary.Objects }
                         *     
                         */
                        public void setObjects(EtherCATInfoV2old.Descriptions.Devices.Device.Profile.Dictionary.Objects value) {
                            this.objects = value;
                        }


                        /**
                         * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
                         * 
                         * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
                         * 
                         * <pre>
                         * &lt;complexType>
                         *   &lt;complexContent>
                         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                         *       &lt;sequence>
                         *         &lt;element name="DataType" minOccurs="0">
                         *           &lt;complexType>
                         *             &lt;complexContent>
                         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                         *                 &lt;sequence>
                         *                   &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                         *                   &lt;element name="BitSize" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/>
                         *                 &lt;/sequence>
                         *               &lt;/restriction>
                         *             &lt;/complexContent>
                         *           &lt;/complexType>
                         *         &lt;/element>
                         *       &lt;/sequence>
                         *     &lt;/restriction>
                         *   &lt;/complexContent>
                         * &lt;/complexType>
                         * </pre>
                         * 
                         * 
                         */
                        @XmlAccessorType(XmlAccessType.FIELD)
                        @XmlType(name = "", propOrder = {
                            "dataType"
                        })
                        public static class DataTypes {

                            @XmlElement(name = "DataType")
                            protected EtherCATInfoV2old.Descriptions.Devices.Device.Profile.Dictionary.DataTypes.DataType dataType;

                            /**
                             * dataType �Ӽ��� ���� �����ɴϴ�.
                             * 
                             * @return
                             *     possible object is
                             *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.Profile.Dictionary.DataTypes.DataType }
                             *     
                             */
                            public EtherCATInfoV2old.Descriptions.Devices.Device.Profile.Dictionary.DataTypes.DataType getDataType() {
                                return dataType;
                            }

                            /**
                             * dataType �Ӽ��� ���� �����մϴ�.
                             * 
                             * @param value
                             *     allowed object is
                             *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.Profile.Dictionary.DataTypes.DataType }
                             *     
                             */
                            public void setDataType(EtherCATInfoV2old.Descriptions.Devices.Device.Profile.Dictionary.DataTypes.DataType value) {
                                this.dataType = value;
                            }


                            /**
                             * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
                             * 
                             * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
                             * 
                             * <pre>
                             * &lt;complexType>
                             *   &lt;complexContent>
                             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                             *       &lt;sequence>
                             *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                             *         &lt;element name="BitSize" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/>
                             *       &lt;/sequence>
                             *     &lt;/restriction>
                             *   &lt;/complexContent>
                             * &lt;/complexType>
                             * </pre>
                             * 
                             * 
                             */
                            @XmlAccessorType(XmlAccessType.FIELD)
                            @XmlType(name = "", propOrder = {
                                "name",
                                "bitSize"
                            })
                            public static class DataType {

                                @XmlElement(name = "Name")
                                protected String name;
                                @XmlElement(name = "BitSize")
                                @XmlSchemaType(name = "unsignedByte")
                                protected Short bitSize;

                                /**
                                 * name �Ӽ��� ���� �����ɴϴ�.
                                 * 
                                 * @return
                                 *     possible object is
                                 *     {@link String }
                                 *     
                                 */
                                public String getName() {
                                    return name;
                                }

                                /**
                                 * name �Ӽ��� ���� �����մϴ�.
                                 * 
                                 * @param value
                                 *     allowed object is
                                 *     {@link String }
                                 *     
                                 */
                                public void setName(String value) {
                                    this.name = value;
                                }

                                /**
                                 * bitSize �Ӽ��� ���� �����ɴϴ�.
                                 * 
                                 * @return
                                 *     possible object is
                                 *     {@link Short }
                                 *     
                                 */
                                public Short getBitSize() {
                                    return bitSize;
                                }

                                /**
                                 * bitSize �Ӽ��� ���� �����մϴ�.
                                 * 
                                 * @param value
                                 *     allowed object is
                                 *     {@link Short }
                                 *     
                                 */
                                public void setBitSize(Short value) {
                                    this.bitSize = value;
                                }

                            }

                        }


                        /**
                         * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
                         * 
                         * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
                         * 
                         * <pre>
                         * &lt;complexType>
                         *   &lt;complexContent>
                         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                         *       &lt;sequence>
                         *         &lt;element name="Object" minOccurs="0">
                         *           &lt;complexType>
                         *             &lt;complexContent>
                         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                         *                 &lt;sequence>
                         *                   &lt;element name="Index" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                         *                   &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                         *                   &lt;element name="Type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                         *                   &lt;element name="BitSize" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/>
                         *                   &lt;element name="Info" minOccurs="0">
                         *                     &lt;complexType>
                         *                       &lt;complexContent>
                         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                         *                           &lt;sequence>
                         *                             &lt;element name="DefaultData" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/>
                         *                           &lt;/sequence>
                         *                         &lt;/restriction>
                         *                       &lt;/complexContent>
                         *                     &lt;/complexType>
                         *                   &lt;/element>
                         *                   &lt;element name="Flags" minOccurs="0">
                         *                     &lt;complexType>
                         *                       &lt;complexContent>
                         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                         *                           &lt;sequence>
                         *                             &lt;element name="Access" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                         *                             &lt;element name="Category" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                         *                             &lt;element name="PdoMapping" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                         *                           &lt;/sequence>
                         *                         &lt;/restriction>
                         *                       &lt;/complexContent>
                         *                     &lt;/complexType>
                         *                   &lt;/element>
                         *                 &lt;/sequence>
                         *               &lt;/restriction>
                         *             &lt;/complexContent>
                         *           &lt;/complexType>
                         *         &lt;/element>
                         *       &lt;/sequence>
                         *     &lt;/restriction>
                         *   &lt;/complexContent>
                         * &lt;/complexType>
                         * </pre>
                         * 
                         * 
                         */
                        @XmlAccessorType(XmlAccessType.FIELD)
                        @XmlType(name = "", propOrder = {
                            "object"
                        })
                        public static class Objects {

                            @XmlElement(name = "Object")
                            protected EtherCATInfoV2old.Descriptions.Devices.Device.Profile.Dictionary.Objects.Object object;

                            /**
                             * object �Ӽ��� ���� �����ɴϴ�.
                             * 
                             * @return
                             *     possible object is
                             *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.Profile.Dictionary.Objects.Object }
                             *     
                             */
                            public EtherCATInfoV2old.Descriptions.Devices.Device.Profile.Dictionary.Objects.Object getObject() {
                                return object;
                            }

                            /**
                             * object �Ӽ��� ���� �����մϴ�.
                             * 
                             * @param value
                             *     allowed object is
                             *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.Profile.Dictionary.Objects.Object }
                             *     
                             */
                            public void setObject(EtherCATInfoV2old.Descriptions.Devices.Device.Profile.Dictionary.Objects.Object value) {
                                this.object = value;
                            }


                            /**
                             * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
                             * 
                             * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
                             * 
                             * <pre>
                             * &lt;complexType>
                             *   &lt;complexContent>
                             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                             *       &lt;sequence>
                             *         &lt;element name="Index" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                             *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                             *         &lt;element name="Type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                             *         &lt;element name="BitSize" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/>
                             *         &lt;element name="Info" minOccurs="0">
                             *           &lt;complexType>
                             *             &lt;complexContent>
                             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                             *                 &lt;sequence>
                             *                   &lt;element name="DefaultData" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/>
                             *                 &lt;/sequence>
                             *               &lt;/restriction>
                             *             &lt;/complexContent>
                             *           &lt;/complexType>
                             *         &lt;/element>
                             *         &lt;element name="Flags" minOccurs="0">
                             *           &lt;complexType>
                             *             &lt;complexContent>
                             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                             *                 &lt;sequence>
                             *                   &lt;element name="Access" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                             *                   &lt;element name="Category" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                             *                   &lt;element name="PdoMapping" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                             *                 &lt;/sequence>
                             *               &lt;/restriction>
                             *             &lt;/complexContent>
                             *           &lt;/complexType>
                             *         &lt;/element>
                             *       &lt;/sequence>
                             *     &lt;/restriction>
                             *   &lt;/complexContent>
                             * &lt;/complexType>
                             * </pre>
                             * 
                             * 
                             */
                            @XmlAccessorType(XmlAccessType.FIELD)
                            @XmlType(name = "", propOrder = {
                                "index",
                                "name",
                                "type",
                                "bitSize",
                                "info",
                                "flags"
                            })
                            public static class Object {

                                @XmlElement(name = "Index")
                                protected String index;
                                @XmlElement(name = "Name")
                                protected String name;
                                @XmlElement(name = "Type")
                                protected String type;
                                @XmlElement(name = "BitSize")
                                @XmlSchemaType(name = "unsignedByte")
                                protected Short bitSize;
                                @XmlElement(name = "Info")
                                protected EtherCATInfoV2old.Descriptions.Devices.Device.Profile.Dictionary.Objects.Object.Info info;
                                @XmlElement(name = "Flags")
                                protected EtherCATInfoV2old.Descriptions.Devices.Device.Profile.Dictionary.Objects.Object.Flags flags;

                                /**
                                 * index �Ӽ��� ���� �����ɴϴ�.
                                 * 
                                 * @return
                                 *     possible object is
                                 *     {@link String }
                                 *     
                                 */
                                public String getIndex() {
                                    return index;
                                }

                                /**
                                 * index �Ӽ��� ���� �����մϴ�.
                                 * 
                                 * @param value
                                 *     allowed object is
                                 *     {@link String }
                                 *     
                                 */
                                public void setIndex(String value) {
                                    this.index = value;
                                }

                                /**
                                 * name �Ӽ��� ���� �����ɴϴ�.
                                 * 
                                 * @return
                                 *     possible object is
                                 *     {@link String }
                                 *     
                                 */
                                public String getName() {
                                    return name;
                                }

                                /**
                                 * name �Ӽ��� ���� �����մϴ�.
                                 * 
                                 * @param value
                                 *     allowed object is
                                 *     {@link String }
                                 *     
                                 */
                                public void setName(String value) {
                                    this.name = value;
                                }

                                /**
                                 * type �Ӽ��� ���� �����ɴϴ�.
                                 * 
                                 * @return
                                 *     possible object is
                                 *     {@link String }
                                 *     
                                 */
                                public String getType() {
                                    return type;
                                }

                                /**
                                 * type �Ӽ��� ���� �����մϴ�.
                                 * 
                                 * @param value
                                 *     allowed object is
                                 *     {@link String }
                                 *     
                                 */
                                public void setType(String value) {
                                    this.type = value;
                                }

                                /**
                                 * bitSize �Ӽ��� ���� �����ɴϴ�.
                                 * 
                                 * @return
                                 *     possible object is
                                 *     {@link Short }
                                 *     
                                 */
                                public Short getBitSize() {
                                    return bitSize;
                                }

                                /**
                                 * bitSize �Ӽ��� ���� �����մϴ�.
                                 * 
                                 * @param value
                                 *     allowed object is
                                 *     {@link Short }
                                 *     
                                 */
                                public void setBitSize(Short value) {
                                    this.bitSize = value;
                                }

                                /**
                                 * info �Ӽ��� ���� �����ɴϴ�.
                                 * 
                                 * @return
                                 *     possible object is
                                 *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.Profile.Dictionary.Objects.Object.Info }
                                 *     
                                 */
                                public EtherCATInfoV2old.Descriptions.Devices.Device.Profile.Dictionary.Objects.Object.Info getInfo() {
                                    return info;
                                }

                                /**
                                 * info �Ӽ��� ���� �����մϴ�.
                                 * 
                                 * @param value
                                 *     allowed object is
                                 *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.Profile.Dictionary.Objects.Object.Info }
                                 *     
                                 */
                                public void setInfo(EtherCATInfoV2old.Descriptions.Devices.Device.Profile.Dictionary.Objects.Object.Info value) {
                                    this.info = value;
                                }

                                /**
                                 * flags �Ӽ��� ���� �����ɴϴ�.
                                 * 
                                 * @return
                                 *     possible object is
                                 *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.Profile.Dictionary.Objects.Object.Flags }
                                 *     
                                 */
                                public EtherCATInfoV2old.Descriptions.Devices.Device.Profile.Dictionary.Objects.Object.Flags getFlags() {
                                    return flags;
                                }

                                /**
                                 * flags �Ӽ��� ���� �����մϴ�.
                                 * 
                                 * @param value
                                 *     allowed object is
                                 *     {@link EtherCATInfoV2old.Descriptions.Devices.Device.Profile.Dictionary.Objects.Object.Flags }
                                 *     
                                 */
                                public void setFlags(EtherCATInfoV2old.Descriptions.Devices.Device.Profile.Dictionary.Objects.Object.Flags value) {
                                    this.flags = value;
                                }


                                /**
                                 * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
                                 * 
                                 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
                                 * 
                                 * <pre>
                                 * &lt;complexType>
                                 *   &lt;complexContent>
                                 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                                 *       &lt;sequence>
                                 *         &lt;element name="Access" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                                 *         &lt;element name="Category" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                                 *         &lt;element name="PdoMapping" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                                 *       &lt;/sequence>
                                 *     &lt;/restriction>
                                 *   &lt;/complexContent>
                                 * &lt;/complexType>
                                 * </pre>
                                 * 
                                 * 
                                 */
                                @XmlAccessorType(XmlAccessType.FIELD)
                                @XmlType(name = "", propOrder = {
                                    "access",
                                    "category",
                                    "pdoMapping"
                                })
                                public static class Flags {

                                    @XmlElement(name = "Access")
                                    protected String access;
                                    @XmlElement(name = "Category")
                                    protected String category;
                                    @XmlElement(name = "PdoMapping")
                                    protected String pdoMapping;

                                    /**
                                     * access �Ӽ��� ���� �����ɴϴ�.
                                     * 
                                     * @return
                                     *     possible object is
                                     *     {@link String }
                                     *     
                                     */
                                    public String getAccess() {
                                        return access;
                                    }

                                    /**
                                     * access �Ӽ��� ���� �����մϴ�.
                                     * 
                                     * @param value
                                     *     allowed object is
                                     *     {@link String }
                                     *     
                                     */
                                    public void setAccess(String value) {
                                        this.access = value;
                                    }

                                    /**
                                     * category �Ӽ��� ���� �����ɴϴ�.
                                     * 
                                     * @return
                                     *     possible object is
                                     *     {@link String }
                                     *     
                                     */
                                    public String getCategory() {
                                        return category;
                                    }

                                    /**
                                     * category �Ӽ��� ���� �����մϴ�.
                                     * 
                                     * @param value
                                     *     allowed object is
                                     *     {@link String }
                                     *     
                                     */
                                    public void setCategory(String value) {
                                        this.category = value;
                                    }

                                    /**
                                     * pdoMapping �Ӽ��� ���� �����ɴϴ�.
                                     * 
                                     * @return
                                     *     possible object is
                                     *     {@link String }
                                     *     
                                     */
                                    public String getPdoMapping() {
                                        return pdoMapping;
                                    }

                                    /**
                                     * pdoMapping �Ӽ��� ���� �����մϴ�.
                                     * 
                                     * @param value
                                     *     allowed object is
                                     *     {@link String }
                                     *     
                                     */
                                    public void setPdoMapping(String value) {
                                        this.pdoMapping = value;
                                    }

                                }


                                /**
                                 * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
                                 * 
                                 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
                                 * 
                                 * <pre>
                                 * &lt;complexType>
                                 *   &lt;complexContent>
                                 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                                 *       &lt;sequence>
                                 *         &lt;element name="DefaultData" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/>
                                 *       &lt;/sequence>
                                 *     &lt;/restriction>
                                 *   &lt;/complexContent>
                                 * &lt;/complexType>
                                 * </pre>
                                 * 
                                 * 
                                 */
                                @XmlAccessorType(XmlAccessType.FIELD)
                                @XmlType(name = "", propOrder = {
                                    "defaultData"
                                })
                                public static class Info {

                                    @XmlElement(name = "DefaultData")
                                    @XmlSchemaType(name = "unsignedByte")
                                    protected Short defaultData;

                                    /**
                                     * defaultData �Ӽ��� ���� �����ɴϴ�.
                                     * 
                                     * @return
                                     *     possible object is
                                     *     {@link Short }
                                     *     
                                     */
                                    public Short getDefaultData() {
                                        return defaultData;
                                    }

                                    /**
                                     * defaultData �Ӽ��� ���� �����մϴ�.
                                     * 
                                     * @param value
                                     *     allowed object is
                                     *     {@link Short }
                                     *     
                                     */
                                    public void setDefaultData(Short value) {
                                        this.defaultData = value;
                                    }

                                }

                            }

                        }

                    }

                }


                /**
                 * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
                 * 
                 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
                 * 
                 * <pre>
                 * &lt;complexType>
                 *   &lt;complexContent>
                 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *       &lt;sequence>
                 *         &lt;element name="Index" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                 *         &lt;element name="Entry" maxOccurs="unbounded" minOccurs="0">
                 *           &lt;complexType>
                 *             &lt;complexContent>
                 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                 &lt;sequence>
                 *                   &lt;element name="Index" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                 *                   &lt;element name="SubIndex" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/>
                 *                   &lt;element name="BitLen" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/>
                 *                   &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                 *                   &lt;element name="Comment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                 *                   &lt;element name="DataType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                 *                 &lt;/sequence>
                 *               &lt;/restriction>
                 *             &lt;/complexContent>
                 *           &lt;/complexType>
                 *         &lt;/element>
                 *       &lt;/sequence>
                 *       &lt;attribute name="Mandatory" type="{http://www.w3.org/2001/XMLSchema}boolean" />
                 *       &lt;attribute name="Fixed" type="{http://www.w3.org/2001/XMLSchema}boolean" />
                 *       &lt;attribute name="Sm" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" />
                 *     &lt;/restriction>
                 *   &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 * 
                 * 
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                    "index",
                    "name",
                    "entry"
                })
                public static class RxPdo {

                    @XmlElement(name = "Index")
                    protected String index;
                    @XmlElement(name = "Name")
                    protected String name;
                    @XmlElement(name = "Entry")
                    protected List<EtherCATInfoV2old.Descriptions.Devices.Device.RxPdo.Entry> entry;
                    @XmlAttribute(name = "Mandatory")
                    protected Boolean mandatory;
                    @XmlAttribute(name = "Fixed")
                    protected Boolean fixed;
                    @XmlAttribute(name = "Sm")
                    @XmlSchemaType(name = "unsignedByte")
                    protected Short sm;

                    /**
                     * index �Ӽ��� ���� �����ɴϴ�.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getIndex() {
                        return index;
                    }

                    /**
                     * index �Ӽ��� ���� �����մϴ�.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setIndex(String value) {
                        this.index = value;
                    }

                    /**
                     * name �Ӽ��� ���� �����ɴϴ�.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getName() {
                        return name;
                    }

                    /**
                     * name �Ӽ��� ���� �����մϴ�.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setName(String value) {
                        this.name = value;
                    }

                    /**
                     * Gets the value of the entry property.
                     * 
                     * <p>
                     * This accessor method returns a reference to the live list,
                     * not a snapshot. Therefore any modification you make to the
                     * returned list will be present inside the JAXB object.
                     * This is why there is not a <CODE>set</CODE> method for the entry property.
                     * 
                     * <p>
                     * For example, to add a new item, do as follows:
                     * <pre>
                     *    getEntry().add(newItem);
                     * </pre>
                     * 
                     * 
                     * <p>
                     * Objects of the following type(s) are allowed in the list
                     * {@link EtherCATInfoV2old.Descriptions.Devices.Device.RxPdo.Entry }
                     * 
                     * 
                     */
                    public List<EtherCATInfoV2old.Descriptions.Devices.Device.RxPdo.Entry> getEntry() {
                        if (entry == null) {
                            entry = new ArrayList<EtherCATInfoV2old.Descriptions.Devices.Device.RxPdo.Entry>();
                        }
                        return this.entry;
                    }

                    /**
                     * mandatory �Ӽ��� ���� �����ɴϴ�.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Boolean }
                     *     
                     */
                    public Boolean isMandatory() {
                        return mandatory;
                    }

                    /**
                     * mandatory �Ӽ��� ���� �����մϴ�.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Boolean }
                     *     
                     */
                    public void setMandatory(Boolean value) {
                        this.mandatory = value;
                    }

                    /**
                     * fixed �Ӽ��� ���� �����ɴϴ�.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Boolean }
                     *     
                     */
                    public Boolean isFixed() {
                        return fixed;
                    }

                    /**
                     * fixed �Ӽ��� ���� �����մϴ�.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Boolean }
                     *     
                     */
                    public void setFixed(Boolean value) {
                        this.fixed = value;
                    }

                    /**
                     * sm �Ӽ��� ���� �����ɴϴ�.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Short }
                     *     
                     */
                    public Short getSm() {
                        return sm;
                    }

                    /**
                     * sm �Ӽ��� ���� �����մϴ�.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Short }
                     *     
                     */
                    public void setSm(Short value) {
                        this.sm = value;
                    }


                    /**
                     * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
                     * 
                     * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
                     * 
                     * <pre>
                     * &lt;complexType>
                     *   &lt;complexContent>
                     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                     *       &lt;sequence>
                     *         &lt;element name="Index" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                     *         &lt;element name="SubIndex" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/>
                     *         &lt;element name="BitLen" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/>
                     *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                     *         &lt;element name="Comment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                     *         &lt;element name="DataType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                     *       &lt;/sequence>
                     *     &lt;/restriction>
                     *   &lt;/complexContent>
                     * &lt;/complexType>
                     * </pre>
                     * 
                     * 
                     */
                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "", propOrder = {
                        "index",
                        "subIndex",
                        "bitLen",
                        "name",
                        "comment",
                        "dataType"
                    })
                    public static class Entry {

                        @XmlElement(name = "Index")
                        protected String index;
                        @XmlElement(name = "SubIndex")
                        @XmlSchemaType(name = "unsignedByte")
                        protected Short subIndex;
                        @XmlElement(name = "BitLen")
                        @XmlSchemaType(name = "unsignedByte")
                        protected Short bitLen;
                        @XmlElement(name = "Name")
                        protected String name;
                        @XmlElement(name = "Comment")
                        protected String comment;
                        @XmlElement(name = "DataType")
                        protected String dataType;

                        /**
                         * index �Ӽ��� ���� �����ɴϴ�.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getIndex() {
                            return index;
                        }

                        /**
                         * index �Ӽ��� ���� �����մϴ�.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setIndex(String value) {
                            this.index = value;
                        }

                        /**
                         * subIndex �Ӽ��� ���� �����ɴϴ�.
                         * 
                         * @return
                         *     possible object is
                         *     {@link Short }
                         *     
                         */
                        public Short getSubIndex() {
                            return subIndex;
                        }

                        /**
                         * subIndex �Ӽ��� ���� �����մϴ�.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link Short }
                         *     
                         */
                        public void setSubIndex(Short value) {
                            this.subIndex = value;
                        }

                        /**
                         * bitLen �Ӽ��� ���� �����ɴϴ�.
                         * 
                         * @return
                         *     possible object is
                         *     {@link Short }
                         *     
                         */
                        public Short getBitLen() {
                            return bitLen;
                        }

                        /**
                         * bitLen �Ӽ��� ���� �����մϴ�.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link Short }
                         *     
                         */
                        public void setBitLen(Short value) {
                            this.bitLen = value;
                        }

                        /**
                         * name �Ӽ��� ���� �����ɴϴ�.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getName() {
                            return name;
                        }

                        /**
                         * name �Ӽ��� ���� �����մϴ�.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setName(String value) {
                            this.name = value;
                        }

                        /**
                         * comment �Ӽ��� ���� �����ɴϴ�.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getComment() {
                            return comment;
                        }

                        /**
                         * comment �Ӽ��� ���� �����մϴ�.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setComment(String value) {
                            this.comment = value;
                        }

                        /**
                         * dataType �Ӽ��� ���� �����ɴϴ�.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getDataType() {
                            return dataType;
                        }

                        /**
                         * dataType �Ӽ��� ���� �����մϴ�.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setDataType(String value) {
                            this.dataType = value;
                        }

                    }

                }


                /**
                 * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
                 * 
                 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
                 * 
                 * <pre>
                 * &lt;complexType>
                 *   &lt;simpleContent>
                 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
                 *       &lt;attribute name="MinSize" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" />
                 *       &lt;attribute name="MaxSize" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" />
                 *       &lt;attribute name="DefaultSize" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" />
                 *       &lt;attribute name="StartAddress" type="{http://www.w3.org/2001/XMLSchema}string" />
                 *       &lt;attribute name="ControlByte" type="{http://www.w3.org/2001/XMLSchema}string" />
                 *       &lt;attribute name="Enable" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" />
                 *     &lt;/extension>
                 *   &lt;/simpleContent>
                 * &lt;/complexType>
                 * </pre>
                 * 
                 * 
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                    "value"
                })
                public static class Sm {

                    @XmlValue
                    protected String value;
                    @XmlAttribute(name = "MinSize")
                    @XmlSchemaType(name = "unsignedByte")
                    protected Short minSize;
                    @XmlAttribute(name = "MaxSize")
                    @XmlSchemaType(name = "unsignedByte")
                    protected Short maxSize;
                    @XmlAttribute(name = "DefaultSize")
                    @XmlSchemaType(name = "unsignedByte")
                    protected Short defaultSize;
                    @XmlAttribute(name = "StartAddress")
                    protected String startAddress;
                    @XmlAttribute(name = "ControlByte")
                    protected String controlByte;
                    @XmlAttribute(name = "Enable")
                    @XmlSchemaType(name = "unsignedByte")
                    protected Short enable;

                    /**
                     * value �Ӽ��� ���� �����ɴϴ�.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getValue() {
                        return value;
                    }

                    /**
                     * value �Ӽ��� ���� �����մϴ�.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setValue(String value) {
                        this.value = value;
                    }

                    /**
                     * minSize �Ӽ��� ���� �����ɴϴ�.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Short }
                     *     
                     */
                    public Short getMinSize() {
                        return minSize;
                    }

                    /**
                     * minSize �Ӽ��� ���� �����մϴ�.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Short }
                     *     
                     */
                    public void setMinSize(Short value) {
                        this.minSize = value;
                    }

                    /**
                     * maxSize �Ӽ��� ���� �����ɴϴ�.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Short }
                     *     
                     */
                    public Short getMaxSize() {
                        return maxSize;
                    }

                    /**
                     * maxSize �Ӽ��� ���� �����մϴ�.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Short }
                     *     
                     */
                    public void setMaxSize(Short value) {
                        this.maxSize = value;
                    }

                    /**
                     * defaultSize �Ӽ��� ���� �����ɴϴ�.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Short }
                     *     
                     */
                    public Short getDefaultSize() {
                        return defaultSize;
                    }

                    /**
                     * defaultSize �Ӽ��� ���� �����մϴ�.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Short }
                     *     
                     */
                    public void setDefaultSize(Short value) {
                        this.defaultSize = value;
                    }

                    /**
                     * startAddress �Ӽ��� ���� �����ɴϴ�.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getStartAddress() {
                        return startAddress;
                    }

                    /**
                     * startAddress �Ӽ��� ���� �����մϴ�.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setStartAddress(String value) {
                        this.startAddress = value;
                    }

                    /**
                     * controlByte �Ӽ��� ���� �����ɴϴ�.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getControlByte() {
                        return controlByte;
                    }

                    /**
                     * controlByte �Ӽ��� ���� �����մϴ�.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setControlByte(String value) {
                        this.controlByte = value;
                    }

                    /**
                     * enable �Ӽ��� ���� �����ɴϴ�.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Short }
                     *     
                     */
                    public Short getEnable() {
                        return enable;
                    }

                    /**
                     * enable �Ӽ��� ���� �����մϴ�.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Short }
                     *     
                     */
                    public void setEnable(Short value) {
                        this.enable = value;
                    }

                }


                /**
                 * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
                 * 
                 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
                 * 
                 * <pre>
                 * &lt;complexType>
                 *   &lt;complexContent>
                 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *       &lt;sequence>
                 *         &lt;element name="Index" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                 *         &lt;element name="Entry" maxOccurs="unbounded" minOccurs="0">
                 *           &lt;complexType>
                 *             &lt;complexContent>
                 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                 &lt;sequence>
                 *                   &lt;element name="Index" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                 *                   &lt;element name="SubIndex" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/>
                 *                   &lt;element name="BitLen" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/>
                 *                   &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                 *                   &lt;element name="Comment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                 *                   &lt;element name="DataType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                 *                 &lt;/sequence>
                 *               &lt;/restriction>
                 *             &lt;/complexContent>
                 *           &lt;/complexType>
                 *         &lt;/element>
                 *       &lt;/sequence>
                 *       &lt;attribute name="Fixed" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" />
                 *       &lt;attribute name="Sm" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" />
                 *     &lt;/restriction>
                 *   &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 * 
                 * 
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                    "index",
                    "name",
                    "entry"
                })
                public static class TxPdo {

                    @XmlElement(name = "Index")
                    protected String index;
                    @XmlElement(name = "Name")
                    protected String name;
                    @XmlElement(name = "Entry")
                    protected List<EtherCATInfoV2old.Descriptions.Devices.Device.TxPdo.Entry> entry;
                    @XmlAttribute(name = "Fixed")
                    @XmlSchemaType(name = "unsignedByte")
                    protected Short fixed;
                    @XmlAttribute(name = "Sm")
                    @XmlSchemaType(name = "unsignedByte")
                    protected Short sm;

                    /**
                     * index �Ӽ��� ���� �����ɴϴ�.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getIndex() {
                        return index;
                    }

                    /**
                     * index �Ӽ��� ���� �����մϴ�.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setIndex(String value) {
                        this.index = value;
                    }

                    /**
                     * name �Ӽ��� ���� �����ɴϴ�.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getName() {
                        return name;
                    }

                    /**
                     * name �Ӽ��� ���� �����մϴ�.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setName(String value) {
                        this.name = value;
                    }

                    /**
                     * Gets the value of the entry property.
                     * 
                     * <p>
                     * This accessor method returns a reference to the live list,
                     * not a snapshot. Therefore any modification you make to the
                     * returned list will be present inside the JAXB object.
                     * This is why there is not a <CODE>set</CODE> method for the entry property.
                     * 
                     * <p>
                     * For example, to add a new item, do as follows:
                     * <pre>
                     *    getEntry().add(newItem);
                     * </pre>
                     * 
                     * 
                     * <p>
                     * Objects of the following type(s) are allowed in the list
                     * {@link EtherCATInfoV2old.Descriptions.Devices.Device.TxPdo.Entry }
                     * 
                     * 
                     */
                    public List<EtherCATInfoV2old.Descriptions.Devices.Device.TxPdo.Entry> getEntry() {
                        if (entry == null) {
                            entry = new ArrayList<EtherCATInfoV2old.Descriptions.Devices.Device.TxPdo.Entry>();
                        }
                        return this.entry;
                    }

                    /**
                     * fixed �Ӽ��� ���� �����ɴϴ�.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Short }
                     *     
                     */
                    public Short getFixed() {
                        return fixed;
                    }

                    /**
                     * fixed �Ӽ��� ���� �����մϴ�.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Short }
                     *     
                     */
                    public void setFixed(Short value) {
                        this.fixed = value;
                    }

                    /**
                     * sm �Ӽ��� ���� �����ɴϴ�.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Short }
                     *     
                     */
                    public Short getSm() {
                        return sm;
                    }

                    /**
                     * sm �Ӽ��� ���� �����մϴ�.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Short }
                     *     
                     */
                    public void setSm(Short value) {
                        this.sm = value;
                    }


                    /**
                     * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
                     * 
                     * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
                     * 
                     * <pre>
                     * &lt;complexType>
                     *   &lt;complexContent>
                     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                     *       &lt;sequence>
                     *         &lt;element name="Index" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                     *         &lt;element name="SubIndex" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/>
                     *         &lt;element name="BitLen" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/>
                     *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                     *         &lt;element name="Comment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                     *         &lt;element name="DataType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                     *       &lt;/sequence>
                     *     &lt;/restriction>
                     *   &lt;/complexContent>
                     * &lt;/complexType>
                     * </pre>
                     * 
                     * 
                     */
                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "", propOrder = {
                        "index",
                        "subIndex",
                        "bitLen",
                        "name",
                        "comment",
                        "dataType"
                    })
                    public static class Entry {

                        @XmlElement(name = "Index")
                        protected String index;
                        @XmlElement(name = "SubIndex")
                        @XmlSchemaType(name = "unsignedByte")
                        protected Short subIndex;
                        @XmlElement(name = "BitLen")
                        @XmlSchemaType(name = "unsignedByte")
                        protected Short bitLen;
                        @XmlElement(name = "Name")
                        protected String name;
                        @XmlElement(name = "Comment")
                        protected String comment;
                        @XmlElement(name = "DataType")
                        protected String dataType;

                        /**
                         * index �Ӽ��� ���� �����ɴϴ�.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getIndex() {
                            return index;
                        }

                        /**
                         * index �Ӽ��� ���� �����մϴ�.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setIndex(String value) {
                            this.index = value;
                        }

                        /**
                         * subIndex �Ӽ��� ���� �����ɴϴ�.
                         * 
                         * @return
                         *     possible object is
                         *     {@link Short }
                         *     
                         */
                        public Short getSubIndex() {
                            return subIndex;
                        }

                        /**
                         * subIndex �Ӽ��� ���� �����մϴ�.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link Short }
                         *     
                         */
                        public void setSubIndex(Short value) {
                            this.subIndex = value;
                        }

                        /**
                         * bitLen �Ӽ��� ���� �����ɴϴ�.
                         * 
                         * @return
                         *     possible object is
                         *     {@link Short }
                         *     
                         */
                        public Short getBitLen() {
                            return bitLen;
                        }

                        /**
                         * bitLen �Ӽ��� ���� �����մϴ�.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link Short }
                         *     
                         */
                        public void setBitLen(Short value) {
                            this.bitLen = value;
                        }

                        /**
                         * name �Ӽ��� ���� �����ɴϴ�.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getName() {
                            return name;
                        }

                        /**
                         * name �Ӽ��� ���� �����մϴ�.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setName(String value) {
                            this.name = value;
                        }

                        /**
                         * comment �Ӽ��� ���� �����ɴϴ�.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getComment() {
                            return comment;
                        }

                        /**
                         * comment �Ӽ��� ���� �����մϴ�.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setComment(String value) {
                            this.comment = value;
                        }

                        /**
                         * dataType �Ӽ��� ���� �����ɴϴ�.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getDataType() {
                            return dataType;
                        }

                        /**
                         * dataType �Ӽ��� ���� �����մϴ�.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setDataType(String value) {
                            this.dataType = value;
                        }

                    }

                }


                /**
                 * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
                 * 
                 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
                 * 
                 * <pre>
                 * &lt;complexType>
                 *   &lt;simpleContent>
                 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
                 *       &lt;attribute name="ProductCode" type="{http://www.w3.org/2001/XMLSchema}string" />
                 *       &lt;attribute name="RevisionNo" type="{http://www.w3.org/2001/XMLSchema}string" />
                 *     &lt;/extension>
                 *   &lt;/simpleContent>
                 * &lt;/complexType>
                 * </pre>
                 * 
                 * 
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                    "value"
                })
                public static class Type {

                    @XmlValue
                    protected String value;
                    @XmlAttribute(name = "ProductCode")
                    protected String productCode;
                    @XmlAttribute(name = "RevisionNo")
                    protected String revisionNo;

                    /**
                     * value �Ӽ��� ���� �����ɴϴ�.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getValue() {
                        return value;
                    }

                    /**
                     * value �Ӽ��� ���� �����մϴ�.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setValue(String value) {
                        this.value = value;
                    }

                    /**
                     * productCode �Ӽ��� ���� �����ɴϴ�.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getProductCode() {
                        return productCode;
                    }

                    /**
                     * productCode �Ӽ��� ���� �����մϴ�.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setProductCode(String value) {
                        this.productCode = value;
                    }

                    /**
                     * revisionNo �Ӽ��� ���� �����ɴϴ�.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getRevisionNo() {
                        return revisionNo;
                    }

                    /**
                     * revisionNo �Ӽ��� ���� �����մϴ�.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setRevisionNo(String value) {
                        this.revisionNo = value;
                    }

                }

            }

        }


        /**
         * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
         * 
         * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="Group" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="Type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                   &lt;element name="Name" maxOccurs="unbounded" minOccurs="0">
         *                     &lt;complexType>
         *                       &lt;simpleContent>
         *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
         *                           &lt;attribute name="LcId" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" />
         *                         &lt;/extension>
         *                       &lt;/simpleContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                   &lt;element name="ImageData16x14" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                 &lt;/sequence>
         *                 &lt;attribute name="SortOrder" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" />
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "group"
        })
        public static class Groups {

            @XmlElement(name = "Group")
            protected EtherCATInfoV2old.Descriptions.Groups.Group group;

            /**
             * group �Ӽ��� ���� �����ɴϴ�.
             * 
             * @return
             *     possible object is
             *     {@link EtherCATInfoV2old.Descriptions.Groups.Group }
             *     
             */
            public EtherCATInfoV2old.Descriptions.Groups.Group getGroup() {
                return group;
            }

            /**
             * group �Ӽ��� ���� �����մϴ�.
             * 
             * @param value
             *     allowed object is
             *     {@link EtherCATInfoV2old.Descriptions.Groups.Group }
             *     
             */
            public void setGroup(EtherCATInfoV2old.Descriptions.Groups.Group value) {
                this.group = value;
            }


            /**
             * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
             * 
             * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;sequence>
             *         &lt;element name="Type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *         &lt;element name="Name" maxOccurs="unbounded" minOccurs="0">
             *           &lt;complexType>
             *             &lt;simpleContent>
             *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
             *                 &lt;attribute name="LcId" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" />
             *               &lt;/extension>
             *             &lt;/simpleContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *         &lt;element name="ImageData16x14" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *       &lt;/sequence>
             *       &lt;attribute name="SortOrder" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" />
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "type",
                "name",
                "imageData16X14"
            })
            public static class Group {

                @XmlElement(name = "Type")
                protected String type;
                @XmlElement(name = "Name")
                protected List<EtherCATInfoV2old.Descriptions.Groups.Group.Name> name;
                @XmlElement(name = "ImageData16x14")
                protected String imageData16X14;
                @XmlAttribute(name = "SortOrder")
                @XmlSchemaType(name = "unsignedByte")
                protected Short sortOrder;

                /**
                 * type �Ӽ��� ���� �����ɴϴ�.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getType() {
                    return type;
                }

                /**
                 * type �Ӽ��� ���� �����մϴ�.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setType(String value) {
                    this.type = value;
                }

                /**
                 * Gets the value of the name property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the name property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getName().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link EtherCATInfoV2old.Descriptions.Groups.Group.Name }
                 * 
                 * 
                 */
                public List<EtherCATInfoV2old.Descriptions.Groups.Group.Name> getName() {
                    if (name == null) {
                        name = new ArrayList<EtherCATInfoV2old.Descriptions.Groups.Group.Name>();
                    }
                    return this.name;
                }

                /**
                 * imageData16X14 �Ӽ��� ���� �����ɴϴ�.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getImageData16X14() {
                    return imageData16X14;
                }

                /**
                 * imageData16X14 �Ӽ��� ���� �����մϴ�.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setImageData16X14(String value) {
                    this.imageData16X14 = value;
                }

                /**
                 * sortOrder �Ӽ��� ���� �����ɴϴ�.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Short }
                 *     
                 */
                public Short getSortOrder() {
                    return sortOrder;
                }

                /**
                 * sortOrder �Ӽ��� ���� �����մϴ�.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Short }
                 *     
                 */
                public void setSortOrder(Short value) {
                    this.sortOrder = value;
                }


                /**
                 * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
                 * 
                 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
                 * 
                 * <pre>
                 * &lt;complexType>
                 *   &lt;simpleContent>
                 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
                 *       &lt;attribute name="LcId" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" />
                 *     &lt;/extension>
                 *   &lt;/simpleContent>
                 * &lt;/complexType>
                 * </pre>
                 * 
                 * 
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                    "value"
                })
                public static class Name {

                    @XmlValue
                    protected String value;
                    @XmlAttribute(name = "LcId")
                    @XmlSchemaType(name = "unsignedShort")
                    protected Integer lcId;

                    /**
                     * value �Ӽ��� ���� �����ɴϴ�.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getValue() {
                        return value;
                    }

                    /**
                     * value �Ӽ��� ���� �����մϴ�.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setValue(String value) {
                        this.value = value;
                    }

                    /**
                     * lcId �Ӽ��� ���� �����ɴϴ�.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Integer }
                     *     
                     */
                    public Integer getLcId() {
                        return lcId;
                    }

                    /**
                     * lcId �Ӽ��� ���� �����մϴ�.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Integer }
                     *     
                     */
                    public void setLcId(Integer value) {
                        this.lcId = value;
                    }

                }

            }

        }

    }


    /**
     * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
     * 
     * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="ImageData16x14" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "id",
        "name",
        "imageData16X14"
    })
    public static class Vendor {

        @XmlElement(name = "Id")
        protected String id;
        @XmlElement(name = "Name")
        protected String name;
        @XmlElement(name = "ImageData16x14")
        protected String imageData16X14;

        /**
         * id �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getId() {
            return id;
        }

        /**
         * id �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setId(String value) {
            this.id = value;
        }

        /**
         * name �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getName() {
            return name;
        }

        /**
         * name �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setName(String value) {
            this.name = value;
        }

        /**
         * imageData16X14 �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getImageData16X14() {
            return imageData16X14;
        }

        /**
         * imageData16X14 �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setImageData16X14(String value) {
            this.imageData16X14 = value;
        }

    }

}
