<%-- 
    Document   : carian_Hakmilik_Popup
    Created on : Jun 27, 2011, 12:22:46 PM
    Author     : Murali
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

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

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">
    $(document).ready( function() {

        var len = $(".daerah").length;

        for (var i=0; i<=len; i++){
            $('.hakmilik'+i).click( function() {
                window.open("${pageContext.request.contextPath}/hakmilik?hakmilikDetail&perihalHakmilik="+$(this).text(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
            });
        }
    });

    function popup(id){
        window.open("${pageContext.request.contextPath}/common/maklumat_hakmilik_single?popup&idSyarat="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=300");
    }

    function simpan(){
        var perihalHakmilik = $('#perihalHakmilik').val();
        var idHakmilik = $('#idHakmilik').val();
        var noPtSementara = $('#noPtSementara').val();
        var idPermohonan = $('#idPermohonan').val() ;
        var url = '${pageContext.request.contextPath}/pelupusan/utiliti/laporanTanah?simpan&perihalHakmilik='+perihalHakmilik;
        $.get(url,
        function(data){
            $('#page_div').html(data);
            self.opener.refreshlptn(idHakmilik,noPtSementara,idPermohonan);
            self.close();
        },'html');          
    }

    function refreshlptn(idHakmilik,noPtSementara,idPermohonan){
        var url = '${pageContext.request.contextPath}/pelupusan/utiliti/laporanTanah?refreshpage&idHakmilik='+idHakmilik+'&noPtSementara='+noPtSementara+'&idPermohonan='+idPermohonan;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }
    
    function openFrame(type){        
        var perihalHakmilik = $('#perihalHakmilik').val();
        //        alert(perihalHakmilik);
        NoPrompt();
        var idHakmilik = $('#idHakmilik').val();
        var noPtSementara = $('#noPtSementara').val();
        var idPermohonan = $('#idPermohonan').val() ;
        //    alert(perihalHakmilik);
        window.open("${pageContext.request.contextPath}/pelupusan/utiliti/laporanTanah?openFrame&perihalHakmilik="+perihalHakmilik+"&type="+type+"&idHakmilik="+idHakmilik+"&noPtSementara="+noPtSementara+"&idPermohonan="+idPermohonan, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
        //        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }
        
</script>
<body>
    <script>
        // Allow the user to be warned by default.
        var allowPrompt = true; 
        window.onbeforeunload = WarnUser;
        function WarnUser()
        {   
            if(allowPrompt)
                refreshpage();
            if(allowPrompt)
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

    <s:form beanclass="etanah.view.stripes.pelupusan.utility.UtilitiLaporanTanahActionBean">
        <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
        <s:errors/>
        <s:messages/>
        <s:hidden name="idPermohonan" id="idPermohonan" value="${actionBean.idPermohonan}"/> 
        <s:hidden name="idHakmilik" id="idHakmilik"/>
        <s:hidden name="noPtSementara" id="noPtSementara"/>
        <div class="subtitle">
            <fieldset class="aras1">
                <div id="carianHakmilik">
                    <legend>Carian Hakmilik</legend>
                </div>
                <div class="content" align="center">
                    <table class="tablecloth" border="0">
                        <tr>
                            <td>
                                ID Hakmilik :
                            </td>
                            <td>
                                <s:text name="perihalHakmilik" id="perihalHakmilik" size="25" value="${actionBean.perihalHakmilik}"/>
                                <s:submit name="simpan" value="Simpan" class="btn" onclick="NoPrompt();"/>            
                                <s:button name="" id="kembali" value="Kembali" class="btn" onclick="openFrame('pTanah')"/>
                            </td>
                        </tr>
                    </table>
                </div>
            </fieldset>
        </div>
        <%-- <div class="subtitle">
             <fieldset class="aras1">
                 <legend>
                     Maklumat Hakmilik
                 </legend>
                 <div class="content" align="center">

                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0"
                               id="line">
                    <display:column title="Bil" sortable="true" style="vertical-align:baseline">
                        <c:if test="${line.hakmilik.idHakmilik eq null}">
                            Tiada rekod
                        </c:if>
                        <c:if test="${line.hakmilik.idHakmilik ne null}">
                            ${line_rowNum}
                        </c:if>
                    </display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}" style="vertical-align:baseline"/>
                    <display:column property="hakmilik.noLot" title="Nombor Lot/PT" style="vertical-align:baseline"/>
                    <display:column title="Luas" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah" style="vertical-align:baseline"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" style="vertical-align:baseline"/>
                    <display:column property="hakmilik.maklumatAtasTanah" title="Jenis Pengggunaan Tanah" />
                </display:table>
            </div>
        </fieldset>
    </div>--%>
        <%--<br>
        <br>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Pihak Berkepentingan
                </legend>
                <div class="content" align="center">
                    <display:table name="${actionBean.pihakKepentinganList}" id="line1" class="tablecloth" requestURI="/penguatkuasaan/maklumat_pemohon" pagesize="10">
                        <display:column title="Bil">
                            ${line1_rowNum}
                            <s:hidden name="" class="x${line1_rowNum -1}" value="${line1.pihak.idPihak}"/>
                        </display:column>
                        <display:column property="pihak.nama" title="Nama" />
                        <display:column property="pihak.noPengenalan" title="No. Pengenalan" />
                        <display:column title="Alamat" class="alamat">
                            ${line1.pihak.alamat1}<c:if test="${line1.pihak.alamat2 ne null}">,</c:if>
                            ${line1.pihak.alamat2}<c:if test="${line1.pihak.alamat3 ne null}">,</c:if>
                            ${line1.pihak.alamat3}<c:if test="${line1.pihak.alamat4 ne null}">,</c:if>
                            ${line1.pihak.alamat4}<c:if test="${line1.pihak.poskod ne null}">,</c:if>
                            ${line1.pihak.poskod}<c:if test="${line1.pihak.negeri.kod ne null}">,</c:if>
                            ${line1.pihak.negeri.nama}
                        </display:column>
                        <display:column property="pihak.noTelefon1" title="No. Telefon" />
                        <display:column property="pihak.email" title="Alamat Email" />
                        <display:column property="jenis.nama" title="Status" class="${line1_rowNum}"/>
                    </display:table>

            </div>
        </fieldset>
    </div>
    <br>
    <br>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Waris
            </legend>
            <div class="content" align="center">
                <display:table name="${actionBean.hakmilikWarisList}" id="line2" class="tablecloth" pagesize="10">
                    <display:column title="Bil">
                        ${line2_rowNum}
                        <s:hidden name="x" class="x${line2_rowNum -1}" value=""/>
                    </display:column>
                    <display:column property="nama" title="Nama" />
                    <display:column property="noPengenalan" title="No. Pengenalan" />
                    <display:column title="Syer"> ${line2.syerPembilang }/${line2.syerPenyebut} </display:column>
                    <display:column property="status" title="Status" class="${line2_rowNum}"/>
                </display:table>
            </div>
        </fieldset>
    </div>--%>

    </s:form>
</body>

