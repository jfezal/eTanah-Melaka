<%-- 
    Document   : tukarganti_rumusan
    Created on : Jul 13, 2015, 12:38:25 PM
    Author     : haqqiem
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt"    prefix="c" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<title>Belakang Kaunter | Rumusan</title>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $('form').submit(function() {
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top: ($(window).height() - 50) / 2 + 'px',
                    left: ($(window).width() - 50) / 2 + 'px',
                    width: '50px'
                }
            });
        });
    });
</script>

<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<div align="center"><table style="width:100%" bgcolor="#00FFFF">
        <tr>
            <td width="100%" height="20" >
                <div style="background-color: 00FFFF;" align="center">
                    <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">PENDAFTARAN: Utiliti Tukar Ganti</font>
                </div>
            </td>
        </tr>
    </table></div>

<s:messages />
<s:errors />
<s:form action="/daftar/utiliti_tukarganti">
    <s:hidden name="${actionBean.ku}"/>
    <s:hidden name="${actionBean.kodUrusan}"/>

    <fieldset class="aras1">
        <p class="title">Urusan : ${actionBean.ku.nama}</p>

        <p class=title>Langkah 3: Maklumat Penyerah</p>
        <span style="font-weight:normal;color: black" class=instr>Pastikan maklumat di bawah adalah betul.</span>

        <p>
            <label>Nama Penyerah :</label>
            ${actionBean.penyerahNama}
        </p>
        <p>
            <label>Alamat Penyerah :</label>
            ${actionBean.penyerahAlamat1}
        </p>
        <p>
            <label>&nbsp;</label>
            ${actionBean.penyerahAlamat2}
        </p>
        <p>
            <label>&nbsp;</label>
            ${actionBean.penyerahAlamat3}
        </p>
        <p>
            <label>Bandar :</label>
            ${actionBean.penyerahAlamat4}
        </p>
        <p>
            <label>Poskod :</label>
            ${actionBean.penyerahPoskod}
        </p>
        <p>
            <label>Negeri :</label>
            ${actionBean.penyerahNegeriNama}
        </p>
        <br/>
    </fieldset>
    <br/>


    <fieldset class="aras1">
        <legend>Maklumat Urusan</legend>
        <div align="center">
        <display:table name="${actionBean.senaraiUrusan}" id="row" class="tablecloth" style="width:95%;">
            <display:column title="Bil">
                ${row_rowNum}
            </display:column>
            <display:column title="Urusan" group="1" >
                <b>${row.nama}</b>
            </display:column>
            <display:column title="Hakmilik Terlibat" group="2">
                <c:forEach items="${actionBean.hakmilikPermohonan}" var="senarai" varStatus="a">
                    <c:out value="${senarai.hakmilik.idHakmilik}" />  
                    <br>
                </c:forEach>
            </display:column>

        </display:table></div>
        <br/>

    </fieldset>
    <div align="center"><table style="width:100%" bgcolor="#00FFFF">
            <tr>
                <td align="right">
                    <c:if test="${actionBean.kodUrusan eq 'HKTHK' || actionBean.kodUrusan eq 'HSTHK'}">
                        <s:submit name="Step2" value="Kembali" class="btn" />
                    </c:if>    
                    <s:submit name="Step9" value="Batal" class="btn" />                    
                    <s:submit name="Step5" value="Selesai" class="btn" onclick="return validate(this.form);"/>
                </td>
            </tr>
        </table></div>
    </s:form>
