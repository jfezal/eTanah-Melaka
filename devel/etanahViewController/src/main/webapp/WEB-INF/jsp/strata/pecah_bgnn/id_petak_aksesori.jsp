<%--
    Document   : testing1
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : wan.fairul
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
KEMASUKAN MAKLUMAT PERMOHONAN
<s:form beanclass="etanah.view.strata.urusan">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>ID Petak Aksesori</legend>
            <p>
                <h5>Petak Aksesori Yang Bersangkutan</h5>
                <br>

                <h5>Petak :</h5>
                <s:select name="" multiple="" size="15" ></s:select>
              Move 
                 <s:select name="" multiple="" size="15" ></s:select>

        </p>
            <br>
       </fieldset>
    </div>
                  <label>&nbsp;</label>
              <a href="urusan?showForm17"<s:submit name="Terus" value="Terus" class="btn"/></a>
<br>


    </s:form>