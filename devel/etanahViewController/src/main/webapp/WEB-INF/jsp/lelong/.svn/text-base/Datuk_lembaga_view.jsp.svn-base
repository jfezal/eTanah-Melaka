<%-- 
    Document   : Datuk_lembaga_view
    Created on : May 19, 2011, 6:17:20 PM
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
<script type="text/javascript">

    function getDatokLembaga(kodSuku){
        if(kodSuku != ""){
            var url = '${pageContext.request.contextPath}/lelong/datuk_lembaga?getDatokLembaga&kodSuku='+kodSuku;
            $.get(url,
            function(data){
                $('#display').html(data);
            });
        }
    }
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.lelong.DatukLembagaActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <s:hidden name="senaraiRujukan.kod"/>

        <fieldset class="aras1">
            <legend>Maklumat Datok Lembaga</legend></br>
            <p>
                <label> Nama Suku :</label>
                <font style="text-transform:uppercase;"> ${actionBean.senaraiRujukan.senarai.nama}</font>&nbsp;
            </p>
            <p>
                <label></font>Nama Gelaran :</label>
                <font style="text-transform:uppercase;"> ${actionBean.senaraiRujukan.perihal}</font>&nbsp;
            </p>
            <p>
                <label>Nama Datok Lembaga :</label>
                <font style="text-transform:uppercase;"> ${actionBean.senaraiRujukan.nama}</font>&nbsp;
            </p>
            <p>
                <label>Alamat: </label>
                <font style="text-transform: uppercase">${actionBean.senaraiRujukan.alamat.alamat1}</font> <c:if test="${actionBean.senaraiRujukan.alamat.alamat2 ne null}">,</c:if>
                <font style="text-transform:uppercase;">${actionBean.senaraiRujukan.alamat.alamat2}</font> <c:if test="${actionBean.senaraiRujukan.alamat.alamat3 ne null}">,</c:if>
                <font style="text-transform:uppercase;">${actionBean.senaraiRujukan.alamat.alamat3}</font> <c:if test="${actionBean.senaraiRujukan.alamat.alamat4 ne null}">,</c:if>
                <font style="text-transform:uppercase;">${actionBean.senaraiRujukan.alamat.alamat4}</font> <c:if test="${actionBean.senaraiRujukan.alamat.poskod ne null}">,</c:if>
                ${actionBean.senaraiRujukan.alamat.poskod}<c:if test="${actionBean.senaraiRujukan.alamat.negeri.kod ne null}">,</c:if>
                <font style="text-transform:uppercase;">${actionBean.senaraiRujukan.alamat.negeri.nama}</font> &nbsp;
            </p>

            <p>
                <label>Nombor Telefon :</label>
                <font style="text-transform:uppercase;"> ${actionBean.senaraiRujukan.noTel1}</font>&nbsp;
            </p></br>
        </fieldset>
    </div>
</s:form>

