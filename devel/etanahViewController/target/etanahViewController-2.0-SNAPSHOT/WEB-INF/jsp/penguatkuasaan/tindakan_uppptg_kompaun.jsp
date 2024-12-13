<%-- 
    Document   : tindakan
    Created on : Jan 20, 2010, 6:55:33 PM
    Author     : farah.shafilla
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    
</script>

<s:form beanclass="etanah.view.penguatkuasaan.PenguatkuasaanActionBean" id="form1">
<div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Tindakan UPP PTG
            </legend>
            <div class="content" >
                <p>
                    <label>Laporan :</label>
                        
                            <s:radio name="laporan" id="laporan" value="L" onchange="javaScript:change(this.value)"/> Lengkap
                            <s:radio name="laporan" id="laporan" value="TL" onchange="javaScript:change(this.value)"/> Tidak Lengkap
                        
                    </p>
                    <p id="case">
                        <label>Kes :</label>
                        
                            <s:radio name="kes" id="kes" value="Ada" onchange="javaScript:change(this.value)" /> Ada
                            <s:radio name="kes" id="kes" value="Tiada" onchange="javaScript:change(this.value)"/> Tiada Kes
                    </p>
                    <p id="action">
                        <label>Tindakan :</label>
                        
                            <s:radio name="tindakan" id="tindakan" value="Kompaun" onchange="javaScript:change(this.value)" /> Kompaun
                            <s:radio name="tindakan" id="tindakan" value="Dakwa" onchange="javaScript:change(this.value)" /> Dakwa
                        
                    </p>
                    <p id="desc">
                        <label>Ulasan :</label>                        
                            <s:textarea name="ulasan" rows="5" cols="50"/>                      
                    </p>
                
            </div>
        </fieldset>
    </div>
</s:form>