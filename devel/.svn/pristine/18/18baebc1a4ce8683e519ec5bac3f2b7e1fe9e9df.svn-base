<%-- 
    Document   : perlantikanJurulelong
    Created on : Oct 21, 2011, 11:06:14 AM
    Author     : mazurahayati.yusop
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<script type="text/javascript">

    function validate(){

        if($('#y').is(':checked') == false && $('#n').is(':checked') == false){
            //            alert("Sila Pilih Keputusan");
            return false;
        }
        return true;
    }
    
    function utilitijurulelong(f){
        var queryString = $(f).formSerialize()
        var url = "${pageContext.request.contextPath}/lelong/tabs?utilitijurulelong&"+queryString;
        $.post(url,
        function(data){
            $('#page_div').html(data);
        },'html');

    }




</script>
<s:form beanclass="etanah.view.stripes.lelong.UtilitiTabsTarikhdanJurulelong">

    <s:messages/>
    <s:errors/>&nbsp;
    <c:if test="${actionBean.flag eq false}">
        <fieldset class="aras1">
            <legend>
                Sila Pilih Jurulelong Yang Terlibat
            </legend>
            <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>

            <p>        
                <label><font color="red">*</font> Jurulelong : </label>
                <s:radio id="y" name="kpsn" value="PH"/> Pentadbir Tanah &nbsp;
                <s:radio id="n" name="kpsn" value="JL"/> Jurulelong Berlesen
            </p>

            <p align="center">
                <s:submit name="simpanKpsn2" value="Simpan" class="btn"  onclick="if(validate())doSubmit(this.form, this.name, 'page_div')"/>
            </p>
        </fieldset>
    </c:if>
    <c:if test="${actionBean.flag ne false}">
        <div class="subtitle" >
            <fieldset class="aras1">

                <legend>
                    Jurulelong
                </legend><br>

                <c:if test="${actionBean.juru ne false}">
                    <p><label>Jurulelong : </label>
                        Pentadbir Tanah
                    </p><br>
                </c:if>
                <c:if test="${actionBean.juru eq false}">
                    <p><label>Jurulelong : </label>
                        Jurulelong Berlesen
                    </p><br>
                </c:if>
                    
                <br>
                <p align="center">
                    <s:button name="" id="report" value="Notis Lelongan" class="longBtn" onclick="showReport(this.name);"/>
                </p>
            </fieldset>
        </c:if>
    </s:form>

