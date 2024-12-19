<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<s:form beanclass="etanah.view.stripes.consent.MaklumatRayuanActionBean">
    <s:messages/>
    <s:hidden name="permohonanUrusan.idUrusan"/>

    <c:if test="${actionBean.permohonan.permohonanSebelum eq null}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend></legend>
                <br/><br/>
                <p align="center"><font size="4"> Maklumat ini hanya diperlukan untuk permohonan rayuan sahaja.</font></p>
                <br/><br/><br/><br/>
            </fieldset>
        </div>
    </c:if>
    <c:if test="${actionBean.permohonan.permohonanSebelum ne null}">

        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Rayuan</legend>
                <c:if test="${edit}">
                    <p>
                        <label>Sebab Rayuan :</label>
                        <s:textarea name="permohonanUrusan.sebab" rows="4" cols="70" class="normal_text"/>
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
                </c:if>
                <c:if test="${!edit}">
                    <p>
                        <label>Sebab Rayuan :</label>
                        <c:if test="${actionBean.permohonanUrusan.sebab ne null}"> ${actionBean.permohonanUrusan.sebab}&nbsp; </c:if>
                        <c:if test="${actionBean.permohonanUrusan.sebab eq null}"> Tiada Data </c:if>
                        
                    </p>
                    <br/>
                </c:if>
            </fieldset>
        </div>
    </c:if>
</s:form>

