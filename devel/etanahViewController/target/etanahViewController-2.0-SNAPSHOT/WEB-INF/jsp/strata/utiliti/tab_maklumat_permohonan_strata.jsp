<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://stripes.sourceforge.net/stripes-security.tld" prefix="ss"%>
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.tabs.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet"
      href="pub/styles/tabNavList.css"/>
<style type="text/css">
    .cursor_pointer {
        cursor:pointer;
    }
</style>

<script type="text/javascript">
    $(document).ready(function() {
        $("#tab_strata").tabs();
        $("#tab_strata").tabs('select','#'+'${actionBean.selectedTab}');
    });
    
    function doSubmit(f){
        var form = $(f).formSerialize();   
        f.submit();
    }
</script>

<div id="aa">
    <div class="subtitle">
        <s:errors/>
        <s:messages/>
        <s:form beanclass="etanah.view.strata.utiliti.KemaskiniDataPermohonanStrataActionBean">
            <fieldset class="aras1">
                <legend>Maklumat Permohonan Strata</legend>
                <br/>
                <p>
                    <label for="idPermohonan">ID Permohonan :</label>
                    ${actionBean.permohonan.idPermohonan}
                </p>
                <p>
                    <label for="kodUrusan">Kod Urusan/Urusan :</label>
                    ${actionBean.permohonan.kodUrusan.kod} - ${actionBean.permohonan.kodUrusan.nama}
                </p>
                <br/>
            </fieldset>

            <br/>

            <div id="tab_strata">
                <ul>
                    <li><a href="#maklumat_penyerah" id="tab1">Maklumat Penyerah</a></li>
                    <li><a href="#maklumat_pemohon" id="tab2">Maklumat Pemohon</a></li>
                    <!--<li><a href="#maklumat_perbadanan" id="tab3">Maklumat Perbadanan Pengurusan</a></li>-->
                </ul>
                <div id="maklumat_penyerah">
                    <%@ include file="/WEB-INF/jsp/strata/utiliti/kemaskini_maklumat_penyerah.jsp" %>
                </div>
                <div id="maklumat_pemohon">
                    <%@ include file="/WEB-INF/jsp/strata/utiliti/kemaskini_maklumat_pemohon.jsp" %>
                </div>
                <!--                <div id="maklumat_perbadanan">
                <%--<%@ include file="/WEB-INF/jsp/strata/utiliti/kemaskini_maklumat_perbadanan.jsp" %>--%>
            </div>-->
            </div>
        </s:form>

    </div>
</div>

