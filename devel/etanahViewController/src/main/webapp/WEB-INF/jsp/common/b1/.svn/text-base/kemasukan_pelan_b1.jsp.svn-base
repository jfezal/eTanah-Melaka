<%-- 
    Document   : kemasukan_pelan_manual
    Created on : Dec 17, 2014, 4:47:50 PM
    Author     : faidzal
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<s:form beanclass="etanah.view.stripes.common.b1.KemasukanPelanB1ActionBean" name="carianPelan" id="carianPelan">
    <s:messages/>
    <s:errors/>
    <div class ="subtitle">
            <fieldset class ="aras1">
                <legend>Muat Naik Pelan</legend>
                <p>
                    <s:hidden id="idPlot" name="idPlot"></s:hidden>
                    </p>
                    <p><label><font color="red">*</font>Id Hakmilik QT:</label>
                        <s:select name="idHakmilikQT" id="id">
                            <s:options-collection collection="${actionBean.senaraiHakmilikQT}" value="idHakmilik" label="idHakmilik"/>
                        </s:select>

                </p>                <p><label><font color="red">*</font>Lot:</label>
                        <s:text id="nolot" name="nolot"></s:text>
                    </p>

                    <p><label><font color="red">*</font>Luas:</label>
                        <s:text id="luass" name="luass"></s:text>
                    </p>
                    <p><label><font color="red">*</font>No Pelan Akui:</label>
                        <s:text id="nopelanakui" name="nopelanakui"></s:text>
                    </p>

                    <p><label><font color="red">*</font>Unit Ukur :</label>
                        <s:text id="unitukur" name="unitukur"></s:text>
                    </p>
                    <p><label><font color="red">*</font>Pelan :</label>
                        <s:file name="file"/>
                </p>

                <p align="center"><s:submit name="simpan" value="Simpan" class="btn"/></p>
            </fieldset>
        
    </div>    
</s:form>
