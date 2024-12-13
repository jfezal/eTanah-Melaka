<%--
    Document   : testing1
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : wan.fairul
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
TERIMA JAWAPAN DARI PEMOHON
<s:form beanclass="etanah.view.strata.rayuan">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Terima Jawapan dari Pemohon</legend>
            <p>
                <label>ID Permohonan :</label>

            </p><br>
            <p>
                <label>Tarikh Surat Pertanyaan :</label>

            </p><br>
            <p>
                <label>Tarikh Surat Jawapan :</label>
        <s:text name="tarikhhjwp" id="datepicker1" class="datepicker"/> cth : 31/01/2009
            </p>
            <p> <label>&nbsp;</label>
              <a href=""<s:submit name="Terus" value="Imbas Surat Jawapan" class="btn"/></a>
                        </p>
             <p>
                 <br>
                <label>Permohonan Lengkap :</label> <s:radio name="radio1" value="Ya"/>Ya
            <s:radio name="radio1" value="Tidak"/>Tidak

            </p><br>
          
        </fieldset>
    </div>
    <br>
    
               <p> <label>&nbsp;</label>
              <a href=""<s:submit name="Terus" value="Terus" class="btn"/></a>
                        </p>


    </s:form>