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
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript">
    $(document).ready( function() {

      maximizeWindow();

        var len = $(".daerah").length;

        for (var i=0; i<=len; i++){
            $('.hakmilik'+i).click( function() {
                window.open("${pageContext.request.contextPath}/hakmilik?hakmilikDetail&idHakmilik="+$(this).text(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
            });
        }
    });

    function popup(id){
        window.open("${pageContext.request.contextPath}/common/maklumat_hakmilik_single?popup&idSyarat="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=300");
    }

    function simpan(){
          var idHakmilik = $('#idHakmilik').val();
          var statusPage = $('#statusPage').val();
          
          var url = '${pageContext.request.contextPath}/pelupusan/pihak_kepentingan?simpan&idHakmilik='+idHakmilik+'&statusPage='+statusPage;
           $.get(url,
            function(data){
                $('#page_div').html(data);
                 self.opener.refreshlptn();
                 self.close();
            },'html');          
        }
        function cariHakmilik(f){
//            alert('aa');
          var statusPage = $('#statusPage').val();
          var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pelupusan/pihak_kepentingan?cariHakmilik&statusPage='+statusPage,q,
            function(data){
                $('#page_div').html(data);
//                self.opener.refreshlptn();
            }, 'html');         
        }
        
 function refreshlptn(){
alert('aa');
var url = '${pageContext.request.contextPath}/pelupusan/pihak_kepentingan?refreshpage';
$.get(url,
function(data){
$('#page_div').html(data);
},'html');
}
       function selectRadioBox()
        {
//                alert("test");
        var e= $('#sizeKod').val();
//        alert(e);
//                alert(document.frm.radiomate.value);
        var cnt=0;
        var data = new Array() ;

        for(cnt=0;cnt<e;cnt++)
         {
//                     alert("test1
             if(e=='1'){
                 if(document.frm.radiomate.checked) {
//                     alert(document.frm.checkmate[cnt].value) ;
                 data[cnt] = document.frm.radiomate.value ;
//                     alert(data[cnt])
                 }
             }
             else{
                 if(document.frm.radiomate[cnt].checked) {
//                     alert(document.frm.checkmate[cnt].value) ;
                 data[cnt] = document.frm.radiomate[cnt].value ;
//                     alert(data[cnt])
                 }
                 else{
                     data[cnt] = "T" ;
                 }
             }
          }
            if(confirm("Adakah anda pasti?")) {
            var url = '${pageContext.request.contextPath}/pelupusan/pihak_kepentingan?simpan&idHakmilik='+data ;
            $.get(url,
            function(data){
                $('#page_div').html(data);
                alert("Rekod telah berjaya di masukkan") ;
                self.close() ;
                self.opener.refreshlptn() ;
            },'html');
            }
        }
</script>

<s:form beanclass="etanah.view.stripes.pelupusan.PihakKepentinganActionBean" name="frm">
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:errors/>
    <s:messages/>
    
    <div class="content" id="hakmilik">
            <p>
                <label for="ID Hakmilik">ID Hakmilik :</label>
                <s:text name="idHakmilikCari" id="idHakmilikCari" size="25"/>
                <%--<s:button class="btn" value="Cari" name="new" id="new" onclick="simpan();"/>                
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>--%>
            </p>
            <p>
                <label>&nbsp;</label>
                <font size="2" color="red"><b> atau</b></font>
            </p>
            <p>
                <label for="No Lot">No Lot :</label>
                <s:text name="noLot" id="noLot" size="25"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <font size="2" color="red"><b> atau</b></font>
            </p>
            <p>
                <label for="No Hakmilik">No Hakmilik :</label>
                <s:text name="noHM" id="noHM" size="25"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:submit name="cariHakmilik" value="Cari" class="btn"/>
<!--                <s:button class="btn" value="Cari" name="new" id="new" onclick="cariHakmilik(this.form);"/>                -->
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
        </div>
        <div class="subtitle">
                <p>
            <s:hidden name="${actionBean.sizeKod}" id="sizeKod" value="${actionBean.sizeKod}"/>
            <display:table style="width:100%" class="tablecloth" name="${actionBean.listHakmilik}" pagesize="100" cellpadding="0" cellspacing="0" requestURI="/pelupusan/pihak_kepentingan" id="line">
                            <display:column> <s:radio name="radiomate" id="radiomate" value="${line.idHakmilik}"/></display:column>
                            <display:column title="ID Hakmilik" property="idHakmilik"/>
                            <display:column title="No Lot">
                               ${line.lot.nama} ${line.noLot} 
                            </display:column>
                             <display:column title="No Hakmilik" property="noHakmilik"/>
                            <c:set var="i" value="${i+1}" />
            </display:table>
        </p>
        <p>
            <c:if test="${fn:length(actionBean.listHakmilik) > 0 }">
                <s:button class="btn" value="Pilih" name="pilih" id="pilih" onclick="selectRadioBox();"/>
            </c:if>
        </p>
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