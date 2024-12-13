<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<%--<%@ taglib uri="http://java.sun.com/jstl/core_rt"    prefix="c" %>--%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript">
    function save(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.get(url,q,
        function(data){$('#page_div',opener.document).html(data);

            self.close();}
        ,'html');
    }

    function simpanPetakAk(event, f){

        var q = $(f).formSerialize();
        var url;
        var bangunan = ${actionBean.bangunan};
        var tingkat = ${actionBean.tingkat};
        var petakAks = ${actionBean.petakAks};
        var idPetak = ${actionBean.bgnpetak.idPetak};
        var namaAks = $('#namaAks').val();
        var LokasiAks = $('#LokasiAks').val();
        var kodKegunaanPetakAks = $('#kodKegunaanPetakAks').val();
        var petakBersangkutan = $('#petakBersangkutan').val();
        var pabAksesori = $('#pabAksesori').val();
        alert(bangunan+tingkat+petakAks);
        url = '${pageContext.request.contextPath}/JadualPetakManual?simpanPetakAk&bangunan=' + bangunan + '&tingkat='+tingkat + '&petakAks='+petakAks 
            + '&namaAks='+namaAks + '&LokasiAks=' +LokasiAks + '&kodKegunaanPetakAks=' +kodKegunaanPetakAks + '&idPetak=' +idPetak 
            + '&petakBersangkutan=' +petakBersangkutan + '&pabAksesori=' +pabAksesori;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    $(document).ready(function() {
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });
    });
</script>
<s:errors/>
<s:messages/>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
<s:form beanclass="etanah.view.strata.JadualPetakManual">

    <fieldset class="aras1">
        <legend>Petak Aksesori</legend>
        <s:hidden name="bangunan" id="bangunan"/>
        <s:hidden name="tingkat" id="tingkat"/>
        <s:hidden name="petakAks" id="petakAks"/>
        <s:hidden name="idPetak" id="idPetak" value="${actionBean.bgnpetak.idPetak}"/>
        <p>
            <label>Petak Bersangkutan : </label>
             <s:text name="petakBersangkutan" id="petakBersangkutan" value="${actionBean.bangunan} - ${actionBean.tingkat} - ${actionBean.petakAks}" size="15"/>
        </p>
        <p><label>Petak Aksesori : </label>
            <c:if test="${empty actionBean.senaraiPetakAksesori}">Tiada Data</c:if>
            <c:if test="${!empty actionBean.senaraiPetakAksesori}">
                <c:forEach items="${actionBean.senaraiPetakAksesori}" var="line">
                    A${line.nama}
                </c:forEach>
            </c:if>
        </p>
        <p>
            <label>Masukkan Petak Aksesori :</label>
            A<s:text name="namaAks" id="namaAks" size="5"/>
        </p>
        <p>
            <label>Lokasi :</label>
            <s:text name="LokasiAks" id="LokasiAks" size="40"/>
        </p>
        <p>
            <label>No Pelan Petak Aksesori :</label>
            <s:text name="pabAksesori" id="pabAksesori" size="40"/>
        </p>
        <p>
            <label>Kegunaan Petak Aksesori :</label>
            <s:select name="kodKegunaanPetakAks">
                <%--<s:select name="kodKegunaanPetakAks" value="${actionBean.mhnbgn.kodKegunaanBangunan.kod}">--%>
                <s:option value="">Sila Pilih</s:option>
                <%--<s:options-collection collection="${listUtil.senaraiKodKegunaanPetakAks}" label="nama" value="kod"/>--%>
                <s:options-collection collection="${listUtil.senaraiKodKegunaanPetakAksesori}" label="nama" value="kod"/>
            </s:select>
        </p>
    </fieldset>
    <br>
    <center>
        <s:submit name="simpanPetakAk" value="Simpan" class="btn" onclick="simpanPetakAk(this.name, this.form)"/>&nbsp;
        <s:button name="" id="close" value="Tutup" onclick="self.close();" class="btn"/>&nbsp;
    </center>
    <p>&nbsp;</p>
</s:form>

