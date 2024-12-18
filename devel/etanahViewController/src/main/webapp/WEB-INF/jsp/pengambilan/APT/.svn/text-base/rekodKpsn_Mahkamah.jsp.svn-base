<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt"    prefix="c" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet"
      href="${pageContext.request.contextPath}/pub/styles/jquery.tooltip.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<!--<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>-->
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript">
    $(document).ready(function() {



    });
    
    function tambahBaru(idMohonMahkamah) {
        // alert(idMH);
        window.open("${pageContext.request.contextPath}/pengambilan/rekodKpsn_Mahkamah?hakMilikPopup&idMohonMahkamah=" + idMohonMahkamah, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }

</script>

<div id="laporan">
    <s:form beanclass="etanah.view.stripes.pengambilan.share.common.RekodKpsnMahkamahActionBean">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Keputusan Mahkamah
                </legend>

                <br>
            </fieldset>    
        </div>
        <br>



        <div class="subtitle" id="maklumat_keluasan_tanah">

            <fieldset class="aras1">
                <legend>
                    Carian ID Permohonan / ID Perserahan
                </legend>
                <p>
                    <label for="Id Hakmilik">ID Permohonan / ID Perserahan :</label>
                    <s:text name="p.idPermohonan" id="IDMohon"/> 
                </p>
                <p>
                    <label>&nbsp;</label> 
                    <s:submit name="searchIdMohon" value="Cari" class="btn"/>
                </p>           
            </fieldset>

            <fieldset class="aras1" id="locate">
                <legend>
                    Maklumat Permohonan Mahkamah

                </legend>

                <center>
                    <display:table class="tablecloth" name="${actionBean.listpermohonanMahkamah}" pagesize="30" cellpadding="0" cellspacing="0"
                                   requestURI="/pengambilan/rekodKpsn_Mahkamah" id="line">
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>

                        <display:column title="ID Hakmilik">${line.hamilikPermohonan.hakmilik.idHakmilik}</display:column>
                         <display:column title="Tarikh Perbicaraan">${line.tarikh_bicara}</display:column>
                         <display:column title="Tarikh Keputusan">${line.tarikhTerimaPerintah}</display:column>
                         <display:column title="Ulasan Mahkamah">${line.ulasan_mahkamah}</display:column>

                        
                      
                        <display:column title="Kemaskini">
                            <div align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor = 'pointer';" onclick="tambahBaru('${line.idMm}');" />
                            </div>
                        </display:column>
                       

                    </display:table>
                </center>

                <%-- <s:button name="simpanHakmilik" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>--%>

            </fieldset>

        </div>     

    </s:form>
</div>
