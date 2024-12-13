<%-- 
    Document   : sedia_b2_pelan
    Created on : Aug 15, 2017, 2:46:12 AM
    Author     : john
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript">
     function save(f) {
      var queryString = $(f).formSerialize();
      var url = "${pageContext.request.contextPath}/pembangunan/melaka/muat_naik_pelan?simpanDataHakmilik&" + queryString;
      $.post(url,
              function(data) {
                    $('#page_div', this.document).html(data);
              }, 'html');
    }
    function doSortPaging(url) {
        $.get(url,
                function (data) {
                    $('.displaytag').html(data);
                    $('.popup').each(function () {
                        $(this).html('<u>' + $(this).text() + '</u>');
                    });
                    $('table.tablecloth thead a').each(function () {
                        var url = $(this).attr('href');
                        $(this).attr("href", "javascript:doSortPaging('" + url + "');");

                    });
                    $('.pagelinks a').each(function () {
                        var url = $(this).attr('href');
                        $(this).attr("href", "javascript:doSortPaging('" + url + "');");
                    });
                }
        , 'html');
    }

    function papar(s) {
        window.open("${pageContext.request.contextPath}/pembangunan/melaka/muat_naik_pelan?papar&idPlot=" + s, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1400px,height=800px,scrollbars=yes");
    }

</script>
<script type="text/javascript">
    function cc(a, f) {
        var form = $(f).formSerialize();
        var url = '${pageContext.request.contextPath}/pembangunan/melaka/muat_naik_pelan?filterMukim&daerah=' + a + '&';
        $.post(url, form,
                function (data) {
                    $('#page_div', this.document).html(data);
                }, 'html');
    }

    function seksyenfilter(a, f) {
        var form = $(f).formSerialize();
        var daerah;
        var url = '${pageContext.request.contextPath}/pembangunan/melaka/muat_naik_pelan?filterSeksyen&mukim=' + a + '&';
        $.get(url, form,
                function (data) {
                    $('#page_div', this.document).html(data);
                }, 'html');
    }






</script>
<s:useActionBean beanclass = "etanah.view.ListUtil" var="listUtil"/>

<s:form id="senarai_tugasan" beanclass="etanah.view.stripes.pembangunan.pelan.MuatNaikPelanB2ActionBean" name="form2">
    <s:hidden name="btn"/>
    <s:errors/>
    <s:messages/>    
    <fieldset class="aras1">
        <legend>
            Maklumat Hakmilik Baru
        </legend>

        <p>
            <label>Cawangan Hakmilik :</label>
            <s:select name="cawangan" id="cawangan" >
                <s:option value="">Sila Pilih</s:option>
                <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod" sort="name" />
            </s:select>
        </p>
        <p>
            <label>Daerah :</label>
            <s:select name="daerah" id="daerah"  onchange="cc(this.value,this.form);">
                <s:option value="">Sila Pilih</s:option>
                <s:options-collection collection="${actionBean.listKodDaerah}" value="kod" label="nama" />                    
            </s:select>
        </p>
        <p>
            <label>Bandar/Pekan/Mukim :</label>
            <s:select name="mukim" onchange="seksyenfilter(this.value,this.form);">
                <s:option value="">Sila Pilih</s:option>
                <s:options-collection collection="${actionBean.listBPM}" value="kod" label="nama"/>
            </s:select>
        </p>
        <p><label>Seksyen:</label>
            <s:select name="seksyen">
                <s:option value="">Sila Pilih</s:option>
                <s:options-collection collection="${actionBean.listKodSeksyen}" value="kod" label="nama"/>
            </s:select>
        </p>
        <p>
            <label for="nolot">Jumlah Pajakan :</label>
            <s:text name="jumPajakan" size="20" id="jumPajakan"/>
        </p>
        <p>
            <label>Jenis Hakmilik Sementara :</label>
            <s:select name="kodHakmilikSementara" id="kodHakmilikSementara" >
                <s:option value="">Sila Pilih</s:option>
                <s:options-collection collection="${listUtil.senaraiKodHakmilik}" label="nama" value="kod" sort="nama" />
            </s:select>
        </p>
        <p>
            <label>Jenis Hakmilik Kekal :</label>
            <s:select name="kodHakmilikTetap" id="kodHakmilikTetap" >
                <s:option value="">Sila Pilih</s:option>
                <s:options-collection collection="${listUtil.senaraiKodHakmilik}" label="nama" value="kod" sort="nama" />
            </s:select>
        </p>
        <p>
            <label>&nbsp;</label>
            <s:button name="simpanDataHakmilik" value="Kemaskini" class="btn" onclick="save(this.form)"/>
        </p>
    </fieldset>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Senarai Plot
            </legend>
            <br/>

            <c:set var="row_num" value="${actionBean.__pg_start}"/>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiPlotPelan}" cellpadding="0" cellspacing="0" id="line">
                    <c:set var="row_num" value="${row_num+1}"/>              
                    <display:column title="Bil" class="bil${line_rowNum}">                        
                        ${row_num}
                    </display:column>
                    <display:column title="No Plot">                        
                        ${line.noPlot}                       
                    </display:column>
                    <display:column title="Nama Pembangunan" > ${line.catatan}
                    </display:column>
                    <display:column title="Luas" >${line.luas} ${line.kodUnitLuas.nama}
                    </display:column>

                    <display:column title="Jenis Pemilikan" > ${line.pemilikan.nama}
                    </display:column>
                    <display:column title="Jumlah Pelan B2">                        
                        ${line.bilanganPlot}                       
                    </display:column>     
                    <display:column title="Muat Naik B2">                        
                        <s:button name="muatnaik" value="Terperinci" class="btn"  onclick="papar('${line.idPlot}')"/>                     
                    </display:column>  

                </display:table></div>
        </fieldset>

    </div></s:form>


