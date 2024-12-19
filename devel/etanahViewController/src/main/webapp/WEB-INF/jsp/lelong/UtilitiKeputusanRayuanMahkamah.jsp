<%-- 
    Document   : UtilitiKeputusanRayuanMahkamah
    Created on : Nov 9, 2010, 5:03:36 PM
    Author     : mazurahayati.yusop
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>


<s:form name="UtilitiKeputusanRayuanMahkamah" beanclass="etanah.view.stripes.lelong.UtilitiKeputusanRayuanMahkamahActionBean">
    <s:messages/>
    <s:errors/>
    <%--<s:hidden name="enkuiri.idEnkuiri"/>--%>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Carian
            </legend>
            <p>
                <label> ID Permohonan :</label>
                <s:text name="idPermohonan" maxlength="20" size="31" onblur="this.value=this.value.toUpperCase();"/>
            </p>

            <p align="right">
                <s:submit name="find" value="Cari" class="btn" />
                <%--<s:button name=" " value="Isi Semula" class="btn" onclick="clearText('BayaranBaki');" />--%>
            </p>
        </fieldset>
    </div>

    <c:if test="${actionBean.flag eq true}">

        <div class="subtitle" id="">
            <fieldset class="aras1">
                <legend>
                    Keputusan Rayuan Mahkamah
                </legend>
                <div class="content">
                    <p>
                        <label> Nama Sidang :</label>
                        <s:text id="" name="rujukanluar.namaSidang" onblur="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label> No. Sidang :</label>
                        <s:text id="" name="rujukanluar.noSidang" onblur="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label> Tarikh Sidang :</label>
                        <s:text name="tarikhSidang" id="datepicker" class="datepicker" formatPattern="dd/MM/yyyy"/>&nbsp;
                        <font color="red" size="1">(cth : hh / bb / tttt)</font>
                        &nbsp;
                    </p>
                    <p>
                        <label> No. Fail :</label>
                        <s:text id="" name="rujukanluar.noFail" onblur="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label> No. Rujukan :</label>
                        <s:text id="" name="rujukanluar.noRujukan" onblur="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label> Ulasan :</label>
                        <s:textarea id="" name="rujukanluar.ulasan" cols="50" rows="5" onblur="this.value=this.value.toUpperCase();" />
                    </p>
                    <p>
                        <label> Catatan :</label>
                        <s:textarea id="" name="rujukanluar.catatan" cols="50" rows="5" onblur="this.value=this.value.toUpperCase();" />
                    </p>
                </div>
            </fieldset>
        </div>
        <div class="content" align="right">
            <p>
                <s:submit name="simpanRujukan" id="" value="Simpan" class="btn"/>
                <%--<s:reset name="" value="Isi Semula" class="btn"/>&nbsp;--%>
            </p>

        </div>
    </c:if>
</s:form>

