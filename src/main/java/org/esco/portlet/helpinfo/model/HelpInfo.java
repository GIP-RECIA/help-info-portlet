/**
 * Copyright © 2016 ESUP-Portail (https://www.esup-portail.org/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.esco.portlet.helpinfo.model;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HelpInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 104271367224910613L;
	/**
	 * 
	 */
	private String imgLink;
	private String title;
	private String text;
	private String knowMoreLink;
	private String imgAlt;
	private Set<Rubrique> rubriques;
	
	private String helpUrl;
	private String yepsUrl;
	
	private boolean isAlreadyRead;

	private boolean isYepsAlreadyRead;

	public boolean isYepsAlreadyRead() {
		return isYepsAlreadyRead;
	}

	public void setYepsAlreadyRead(boolean isYepsAlreadyRead) {
		this.isYepsAlreadyRead = isYepsAlreadyRead;
	}

	public boolean isAlreadyRead() {
		return isAlreadyRead;
	}

	public void setAlreadyRead(boolean isAlreadyRead) {
		this.isAlreadyRead = isAlreadyRead;
	}

	public HelpInfo() {
		super();
	}

	@JsonCreator
	public HelpInfo(@JsonProperty(value = "mediaUrl", required = true) String imgLink, @JsonProperty(value = "title", required = true) String title,
					 @JsonProperty(value = "summary", required = true) String text, @JsonProperty(value = "link", required = true) String knowMoreLink,
					 @JsonProperty("rubriques") Set<Rubrique> rubriques
					 ) {
		this.imgLink = imgLink;
		this.title = title;
		this.text = text;
		this.knowMoreLink = knowMoreLink;
		this.rubriques = rubriques;
	}

	public HelpInfo(String imgL, String title, String text, String kmL, String alt){
		super();
		this.imgLink = imgL;
		this.title = title;
		this.text = text;
		this.knowMoreLink = kmL;
		this.imgAlt = alt;
	}

	public String getImgLink() {
		return imgLink;
	}

	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getKnowMoreLink() {
		return knowMoreLink;
	}

	public void setKnowMoreLink(String knowMoreLink) {
		this.knowMoreLink = knowMoreLink;
	}

	public String getImgAlt() {
		return imgAlt;
	}

	public void setImgAlt(String imgAlt) {
		this.imgAlt = imgAlt;
	}

	public Set<Rubrique> getRubriques() {
		return rubriques;
	}

	public void setRubriques(final Set<Rubrique> rubriques) {
		this.rubriques = rubriques;
	}

	@Override
	public String toString() {
		return "HelpInfo{" +
				"imgLink='" + imgLink + '\'' +
				", title='" + title + '\'' +
				", text='" + text + '\'' +
				", knowMoreLink='" + knowMoreLink + '\'' +
				", imgAlt='" + imgAlt + '\'' +
				", rubriques='" + rubriques.toString() + '\'' +
				'}';
	}

	public String getHelpUrl() {
		return helpUrl;
	}

	public void setHelpUrl(String helpUrl) {
		this.helpUrl = helpUrl;
	}

	public String getYepsUrl() {
		return yepsUrl;
	}

	public void setYepsUrl(String yepsUrl) {
		this.yepsUrl = yepsUrl;
	}
}
