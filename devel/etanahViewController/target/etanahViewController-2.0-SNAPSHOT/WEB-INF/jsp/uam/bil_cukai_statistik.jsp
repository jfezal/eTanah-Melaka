<%-- 
    Document   : auditMedan
    Created on : Jul 4, 2011, 7:54:44 PM
    Author     : zahidaziz
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.util.List;" %>
<link type="text/css" rel="stylesheet"
      href="${pageContext.request.contextPath}/pub/styles/jquery.tooltip.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.tools.min.js?select=full&debug=true"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

            <s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>    
    <s:form beanclass="etanah.view.uam.BilCukaiActionBean" name ="auditAplikasiForm" id ="auditAplikasi" onsubmit="return checkFields();">
        <s:messages/>
        <s:errors/>
        <div class ="subtitle displaytag">
            <fieldset class ="aras1">
                <legend>Carian</legend>
                

                    <p><label><font color="red">*</font>Tarikh Dari :</label>
                        <s:text name="tarikhDari" class="datepicker"  formatPattern="dd/MM/yyyy" value = ""></s:text>
                    </p>
                    <p><label><font color="red">*</font>Tarikh Hingga :</label>
                        <s:text name="tarikhHingga" class = "datepicker" formatType="date" formatPattern="dd/MM/yyyy" value = ""></s:text>
                    </p>
                    </p>
                    <p><label>Cawangan :</label>
                    <s:select name="caw">
                        <s:option></s:option>
 <s:options-collection collection="${list.senaraiKodCawangan}" label="name" value="kod" />
                    </s:select>
                </p>
                <div align ="center">
                    <p><s:submit name="cari" value="Cari" class="btn" />&nbsp;<s:reset name = "reset" class="btn" value ="Isi Semula"/></p>
                </div>
            </fieldset>
        </div>

       
            <div class="content" align="center">
                <fieldset class="aras1">
                    <legend>
                        Senarai Statistik
                    </legend>
                    <div class="content" align="center">
                        <display:table  name="${actionBean.senaraiBilCukai}" id="line" class="tablecloth">
                            <display:column title="Bil" sortable = "true" style  = "verticle-align:baseline">${line_rowNum}</display:column>
                            <display:column  property="cawangan" sortable="true" title="Cawangan" style = "verticle-align:baseline" ></display:column>
                            <display:column property="jumlah" sortable="true" title="Jumlah" style = "verticle-align:baseline" ></display:column>
                        </display:table>
                    </div>
                </fieldset>
            </div>

    </s:form>

