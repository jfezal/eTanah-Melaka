<%--
    Document   :  laporan_tanahV2KeadaanTanah.jsp
    Created on :  March 07, 2012, 02:59 PM
    Author     :  Shazwan
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DALAM KAWASAN</title>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
<script type="text/javascript">
    var size = 0;
    function save(event, f) {
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url, q,
                function(data) {
                    $('#page_div', opener.document).html(data);
                    self.close();
                }, 'html');
    }

    $(document).ready(function() {
        //maximizeWindow();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    }); //END OF READY FUNCTION

    function tambahKawasan(idMohonHakmilik, idLaporTanah) {
//       alert(idMohonHakmilik);
//       alert(idLaporTanah);
        window.open("${pageContext.request.contextPath}/common/laporan/tanah/laporantanahNewActionBean?tambahkawasanPopup&idMohonHakmilik=" + idMohonHakmilik + '&idLaporTanah=' + idLaporTanah, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=700,scrollbars=yes");
    }

    function editLaporKawasan(idMohonlaporKws, idMohonHakmilik, idLaporTanah) {
        alert(idMohonlaporKws);
        window.open("${pageContext.request.contextPath}/common/laporan/tanah/laporantanahNewActionBean?tambahkawasanPopup&idMohonlaporKws=" + idMohonlaporKws + '&idMohonHakmilik=' + idMohonHakmilik + '&idLaporTanah=' + idLaporTanah, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }

    function removeLaporKawasan(idMohonlaporKws, idMohonHakmilik, idLaporTanah)
    {
//        NoPrompt();
        if (confirm('Adakah anda pasti untuk menghapus data ini?')) {
//            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/common/laporan/tanah/laporantanahNewActionBean?deletelaporKwsn&idMohonlaporKws=' + idMohonlaporKws + '&idMohonHakmilik=' + idMohonHakmilik + '&idLaporTanah=' + idLaporTanah,
                    function(data) {
                        $('#page_div').html(data);

                    }, 'html');
//            self.refreshpage2('dKawasan');
        }
    }

 

</script>
<body>
    <script>
        // Allow the user to be warned by default.
        var allowPrompt = true;

        window.onbeforeunload = WarnUser;
        function WarnUser()
        {
            if (allowPrompt)
                refreshpage();
            if (allowPrompt)
            {
                event.returnValue = "Segala Maklumat tidak akan disimpan, anda pasti?";
            }
            else
            {
                // Reset the flag to its default value.
                allowPrompt = true;
            }
        }
        function NoPrompt()
        {
            allowPrompt = false;
        }

    </script>
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.stripes.pelupusan.LaporanTanahV2PelupusanActionBean" name="form">
        <s:errors/>
        <s:messages/>
        <s:hidden name="idHakmilik" id="idHakmilik"/>
        <div class="subtitle">
            <fieldset class="aras1">
                <div id="perihaltanah">
                    <legend>Dalam Kawasan</legend>
                    <div class="content" align="center">
                        <table class="tablecloth" border="0">
                            <tr>
                                Tanah Dipohon Berada Dalam Kawasan :
                                <td>
                                    <display:table  name="${actionBean.senaraiLaporanKawasan}" id="line9" class="tablecloth">
                                        <s:hidden name="" class="${line9_rowNum -1}" value="${line9.idMohonlaporKws}"/>
                                        <display:column title="No">${line9_rowNum}</display:column>
                                        <display:column title="Jenis Rizab"  property="kodRizab.nama"/>
                                        <display:column title="Catatan">
                                            <c:if test="${line9.catatan ne null}">
                                                ${line9.catatan}
                                            </c:if>
                                            <c:if test="${line9.catatan eq null}">
                                                -
                                            </c:if>
                                        </display:column>
                                        <display:column title="No Warta" property="noWarta"/>
                                        <display:column title="Tarikh Warta" property="tarikhWarta" format="{0,date,dd-MM-yyyy}"/>
                                        <display:column title="No Pelan Warta" property="noPelanWarta" />
                                        <display:column title="Kemaskini">
                                            <div align="center">
                                                <img height="17px" width="17px" alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png' class='rem'
                                                     id='rem_${line9_rowNum}' onmouseover="this.style.cursor = 'pointer';" onclick="editLaporKawasan('${line9.idMohonlaporKws}', '${actionBean.hakmilikPermohonan.id}', '${actionBean.laporanTanah1.idLaporan}')"/>
                                            </div>
                                        </display:column>
                                        <display:column title="Hapus">
                                            <div align="center">
                                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                     id='rem_${line9_rowNum}' onmouseover="this.style.cursor = 'pointer';" onclick="removeLaporKawasan('${line9.idMohonlaporKws}', '${actionBean.hakmilikPermohonan.id}', '${actionBean.laporanTanah1.idLaporan}')"/>
                                            </div> 
                                        </display:column>
                                    </display:table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <s:button name="dalamKwsn" value="Tambah" class="longbtn"  onclick="tambahKawasan('${actionBean.hakmilikPermohonan.id}', '${actionBean.laporanTanah1.idLaporan}');return false;"/>
                                    <s:button name="tutup" value="Tutup" class="btn" onclick="self.close();"/>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>        
            </fieldset>
        </div>
    </s:form>
</body>

