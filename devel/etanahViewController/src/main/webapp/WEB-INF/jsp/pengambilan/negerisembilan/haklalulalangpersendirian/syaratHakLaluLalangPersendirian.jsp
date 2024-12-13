<%-- 
    Document   : syaratHakLaluLalangPersendirian
    Created on : Okt 21, 2010, 10:34:17 PM
    Author     : massita
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>

<s:form beanclass="etanah.view.stripes.pengambilan.SyaratHakLaluLalangPersendirianActionBean">
    <s:messages/>
    <s:errors/>
    <br/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Keadaan Tanah</legend>
                <br>
                <p>
                    <label>Dilintasi Oleh :</label>
                    <s:checkbox name="a" value="Y"/>&nbsp; Talian Elektrik<br>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:checkbox name="b" value="Y"/>&nbsp; Talian Telefon<br>

                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:checkbox name="c" value="Y"/>&nbsp; Laluan Gas<br>

                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:checkbox name="d" value="Y"/>&nbsp; Paip Air<br>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:checkbox name="e" value="Y"/>&nbsp; Tali Air<br>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:checkbox name="f" value="Y"/>&nbsp; Sungai<br>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:checkbox name="g" value="Y"/>&nbsp; Parit<br>
                </p>
            <br />
            <table align="center">
                   <tr>
                       <td><font color="black" style=" font-size: x-small"><s:submit name="simpan" value="Simpan" class="btn"/></font>
                      <font color="black" style=" font-size: x-small"><s:reset name="reset" value="Isi Semula" class="btn"/></font>&nbsp;
                      </td>
                    </tr>
            </table>
              </fieldset>
   </div>
</s:form>




