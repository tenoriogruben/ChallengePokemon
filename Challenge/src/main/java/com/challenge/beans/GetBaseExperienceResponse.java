//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.05.04 at 12:56:30 PM CDT 
//


package com.challenge.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="base_experience" type="{http://www.challenge.org/pokemonChallenge}baseExp"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "baseExperience"
})
@XmlRootElement(name = "getBaseExperienceResponse")
public class GetBaseExperienceResponse {

    @XmlElement(name = "base_experience", required = true)
    protected BaseExp baseExperience;

    /**
     * Gets the value of the baseExperience property.
     * 
     * @return
     *     possible object is
     *     {@link BaseExp }
     *     
     */
    public BaseExp getBaseExperience() {
        return baseExperience;
    }

    /**
     * Sets the value of the baseExperience property.
     * 
     * @param value
     *     allowed object is
     *     {@link BaseExp }
     *     
     */
    public void setBaseExperience(BaseExp value) {
        this.baseExperience = value;
    }

}
