<%-- 
    Document   : tindakan_pptd_operasi
    Created on : Feb 22, 2010, 10:26:21 AM
    Author     : farah.shafilla
--%>


<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    $('#jenis').hide();
    function change(value){
        if(value == "Ya")
        {
            $('#jenis').show();
          
        }
        else
        {
            $('#jenis').hide();
        }
    }
</script>
<s:form beanclass="etanah.view.penguatkuasaan.PenguatkuasaanActionBean" id="form1">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Kemasukan Halangan
            </legend>
            <div class="content">

                <p>
                    <label>Ada Halangan ?</label>
                    <s:radio name="halangan" id="halangan" value="Ya" onchange="javaScript:change(this.value)"/> Ya
                    <s:radio name="halangan" id="halangan" value="Tidak" onchange="javaScript:change(this.value)"/> Tidak
                </p>

                <p id="jenis">
                    <label>Jenis Halangan :</label>
                    <s:textarea cols="50" rows="5" name="operasi"/>
                </p>
                <p>
                    <label>Jenis Operasi :</label>
                    <s:radio name="radio_" id="radio_" value=""/> Terancang
                    <s:radio name="radio_" id="radio_" value=""/> Segera (Ad-Hoc)
                </p>

            </div>
        </fieldset>
    </div>
</s:form>