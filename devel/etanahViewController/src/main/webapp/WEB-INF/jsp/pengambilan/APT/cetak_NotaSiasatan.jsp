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

    function cetak(idMohon) {
        alert(idMohon);

        var report = 'ACQBrgSiasatKosong_MLK.rdf';
        var url = "reportName=" + report + "%26p_id_mohon=" + idMohon;

        window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url=" + url, "eTanah",
         "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");

        //window.open("$//{pageContext.request.contextPath}/reportGeneratorServlet?" + 'reportName=' + report + "&p_id_mohon=" + idMohon, "eTanah", "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }


</script>

<div id="laporan">
    <s:form beanclass="etanah.view.stripes.pengambilan.sek8.Laporan_DrafNotaSiasatan">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Cetak Draf Nota Siasatan
                </legend>

                <br>
            </fieldset>    
        </div>
        <br>



        <div class="subtitle" id="maklumat_keluasan_tanah">
            <fieldset class="aras1">

                <legend>
                    Draf Nota Siasatan Lengkap
                </legend>
                <p style="color:red">
                    *Sila klik pada (<img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem' id='rem_${line_rowNum}' onmouseover="this.style.cursor = 'pointer';"/>) untuk memaparkan laporan<br/>
                </p>
                <br>
                <fieldset class="aras1">
                    <legend>
                        Carian Hakmilik
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

                <table class="tablecloth">
                    <tr style="width: 100%">
                        <th>Bil</th>
                        <th>Laporan</th>
                        <th>Papar</th>

                    <tr>
                        <td>1
                        </td>
                        <td>Draf Nota Siasatan Lenngkap
                        </td>
                        <td><div align="center">
                                <p align="center">
                                    <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                         onmouseover="this.style.cursor = 'pointer';" onclick="cetak('${actionBean.idPermohonan}');">
                                </p>
                            </div>
                        </td>
                    </tr>
                    </tr>
                </table>
                <br>
            </fieldset>
        </div>     

    </s:form>
</div>
