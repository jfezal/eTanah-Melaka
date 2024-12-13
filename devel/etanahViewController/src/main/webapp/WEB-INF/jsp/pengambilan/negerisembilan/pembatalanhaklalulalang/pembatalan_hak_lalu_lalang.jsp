<%-- 
    Document   : pembatalan_hak_lalu_lalang
    Created on : Oct 26, 2010, 3:20:09 PM
    Author     : massita
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252" language="java"%>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>


<s:form beanclass="etanah.view.stripes.pengambilan.PembatalahHakLaluLalangActionBean">
    <div class="subtitle">
        <s:errors/>
        <s:messages/>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend> </legend>
            <p>
                <label>Lokasi :</label>
                <s:text name="lokasi"/>
            </p>
            <p>
               <label>Tarikh :</label>
                <s:text name="tarikh" class="datepicker"/>
            </p>
            <tr>
                    <td><label>jam :</label></td>
                    <%--<td><s:text name="jam1" size="5"/> : <s:text name="jam2" size="5"/>:
                        <s:select name="jam4" style="width:61px">
                            <s:option value="0">Pilih</s:option>
                            <s:option value="01">PM</s:option>
                            <s:option value="02">AM</s:option>
                        </s:select>
                    </td>--%>
                    <td><s:select name="jam[${line_rowNum - 1}]" style="width:61px" id="jam${line_rowNum - 1}">
                        <s:option value="">Jam</s:option>
                        <s:option value="01">01</s:option>
                        <s:option value="02">02</s:option>
                        <s:option value="03">03</s:option>
                        <s:option value="04">04</s:option>
                        <s:option value="05">05</s:option>
                        <s:option value="06">06</s:option>
                        <s:option value="07">07</s:option>
                        <s:option value="08">08</s:option>
                        <s:option value="09">09</s:option>
                        <s:option value="10">10</s:option>
                        <s:option value="11">11</s:option>
                        <s:option value="12">12</s:option>
                    </s:select>
                    <s:select name="minit[${line_rowNum - 1}]" style="width:72px" id="minit${line_rowNum - 1}">
                        <s:option value="">Minit</s:option>
                        <s:option value="00">00</s:option>
                        <s:option value="05">05</s:option>
                        <s:option value="10">10</s:option>
                        <s:option value="15">15</s:option>
                        <s:option value="20">20</s:option>
                        <s:option value="25">25</s:option>
                        <s:option value="30">30</s:option>
                        <s:option value="35">35</s:option>
                        <s:option value="40">40</s:option>
                        <s:option value="45">45</s:option>
                        <s:option value="50">50</s:option>
                        <s:option value="55">55</s:option>
                    </s:select>
                    <s:select name="ampm[${line_rowNum - 1}]" style="width:61px" id="ampm${line_rowNum - 1}">
                        <s:option value="">Pilih</s:option>
                        <s:option value="AM">AM</s:option>
                        <s:option value="PM">PM</s:option>
                    </s:select></td>
                </tr>
           <p>
                <label>Perkara :</label>
                <s:text name="perkara"/>
            </p>

            <table align="center">
            <tr>
            <td><font style="font-family: Arial; font-size: x-small; font-weight: bold;"><s:button name="" id="" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                <%--<s:button name="" id="" value="jana" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/></font></td></tr>--%>
            </table>
        </fieldset >
        
    </div>
</s:form>
