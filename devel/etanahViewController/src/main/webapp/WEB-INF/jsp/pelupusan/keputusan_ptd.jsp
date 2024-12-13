<%-- 
    Document   : Kuasa PTD
    Created on : July 14, 2012, 12:16 PM
    Author     : Orogenic
	Edited By Shaib Rassul On 12 Sept 2012 - remove radio button
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.stripes.pelupusan.KeputusanPTD_PRIZActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="openPTD" id="openPTD"/>
    <s:hidden name="viewOnly" id="viewOnly"/>
       
    <div class="subtitle">
        <fieldset class="aras1">
            <div>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRIZ'}">
                    <legend>Keputusan PTD</legend>
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRMMK'}">
                    <legend>Sedia Surat Keputusan</legend>
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTG'}">
                    <legend>Sedia Surat Pulang atau Rampas Wang Cagaran</legend>
                </c:if>
                

                <label>Keputusan :</label>
                <p>${actionBean.keputusan}&nbsp;</p>
<!--                <td class="input1">-->
<!--                    <!--s:radio name="fasaMohon.keputusan.kod" value="L"/> Lulus &nbsp;-->
<!--                    <!--s:radio name="fasaMohon.keputusan.kod" value="T"/> Tolak &nbsp;-->
            </div
            <label>&nbsp</label>
            <div>
                <label>Ulasan PTD :</label>
                <c:if test="${!actionBean.viewOnly}">
                    <p><s:textarea cols="50" rows="7" name="fasaMohon.ulasan" class="normal_text"/></p>
                </c:if>
                <c:if test="${actionBean.viewOnly}">
                    <p>${actionBean.ulasan}</p>
                </c:if>
                
            </div>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRIZ' or actionBean.permohonan.kodUrusan.kod eq 'PBRZ'}">
                <p align="center">
                    <s:button name="simpan" class="btn" value="Simpan" onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp
                </p>
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRMMK'}">
                <c:if test="${!actionBean.viewOnly}">
                  <p align="center">
                    <s:button name="simpan" class="btn" value="Simpan" onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp
                    </p>  
                </c:if>    
            </c:if>
        </fieldset>
    </div>
    
    
</s:form>