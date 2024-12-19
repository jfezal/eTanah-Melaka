<%-- 
    Document   : utiliti_tujuan_permohonan
    Created on : Feb 4, 2014, 11:44:57 AM
    Author     : khairul.hazwan
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">  
    function checking(){
      
            var mohon = document.getElementById('idPermohonan');
            if((mohon.value == "")){
                alert('Sila Masukkan ID Permohonan untuk membuat carian Tujuan Permohonan.');
                return false;
            }else{return true}
    }
</script>

<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/pub/styles/styles.css" />
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>                     
<s:form beanclass="etanah.view.stripes.pembangunan.utiliti.UtilitiTujuanPermohonanActionBean">
    <s:messages/>
    <s:hidden name="permohonan.idPermohonan" id="idPermohonanhidden" />
    <c:if test = "${actionBean.idMohonShow}"> 
        <div class="subtitle">
            <fieldset class="aras1">
                <br>
                <legend>Masukkan ID Permohonan Untuk Carian Tujuan Permohonan</legend>

                <label for="idPermohonan"><em>*</em>ID Permohonan :</label>                   
                <input type="text" name="idPermohonan" id="idPermohonan" onkeyup="this.value=this.value.toUpperCase();"/>

                <br>
                <br>
                <p align="center">
                    <s:submit name="checkPermohonan" value="Cari" class="btn" onclick="return checking();"/>             
                </p>
            </fieldset>
        </div>
    </c:if>

    <c:if test = "${actionBean.ltshow}">   
        <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>    
        <div class="subtitle" id="page_effect">
            <fieldset class="aras1">

                <legend>Utiliti Tujuan Permohonan</legend>

                <p>
                    <label>ID Permohonan :</label>
                    <c:if test="${actionBean.permohonan.idPermohonan ne null}">${actionBean.permohonan.idPermohonan}&nbsp;</c:if>
                    <c:if test="${actionBean.permohonan.idPermohonan eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Urusan :</label>
                    <c:if test="${actionBean.permohonan.kodUrusan.nama ne null}">${actionBean.permohonan.kodUrusan.nama}&nbsp;</c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.nama eq null}"> Tiada Data </c:if>
                </p>                
            </fieldset>
                
         <br/>
         <div class="subtitle" id="page_effect2">
            <fieldset class="aras1">
                
                <p>
                    <label><td><b>Tujuan Permohonan : </b></td></label>                     
                    <td><s:textarea name="tujuan" rows="5" cols="130" class="normal_text"/></td>                                      
                </p>
                
            </fieldset>
         </div>
        
         <div class="subtitle" align="center">
            <fieldset class="aras1">          
                <p>
                    
                    <s:submit name="simpan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>                                                 
                    <s:submit name="reset" value="Carian Semula" class="btn" onclick=""/>

                </p>                  
            </fieldset>
         </div>
        
        
    </c:if>
    </s:form>
