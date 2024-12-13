<%-- 
    Document   : kemasukan_warta_mlk
    Created on : Jul 12, 2013, 11:15:38 AM
    Author     : Admin
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%--<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>--%>
<script type="text/javascript">

function validation() {

    if($("#datepicker2").val() == ""){
            alert('Sila pilih " tarikh " terlebih dahulu.');
            $("#datepicker").focus();
            return false;
        }

        if($("#nowarta").val() == ""){
            alert('Sila masukkan " No Warta " terlebih dahulu.');
            $("#nowarta").focus();
            return false;
        }
        return true;
    }


          $(document).ready(function() {
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });
    });

function test(){
           document.getElementById("datepicker").value ="";
           document.getElementById("nowarta").value ="";
       }


function showUlasan() {
     $('#adakesilapan').show();
}
function hideUlasan() {

    $('#adakesilapan').hide();
    <%--$('#adakesilapan').val("");--%>

}
function removeWarta(idRujukan)
{
    if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pengambilan/terima_warta_mlk?deleteWarta&idRujukan='
+idRujukan;
            $.get(url,
            function(data){
            $('#page_div').html(data);
            },'html');
        }
}

 function refreshNotis(){
                var url = '${pageContext.request.contextPath}/pengambilan/terima_warta_mlk?showForm';
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
    }
      function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

     function muatNaikForm(idRujukan) {
 alert("idRujukan"+idRujukan);
        <%--var idRujukan = $('#idRujukan').val();--%>
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/pengambilan/terima_warta_mlk?popupUpload&idRujukan='+idRujukan;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
    }
     function scan(idRujukan) {
        <%--var idRujukan = $('#idRujukan').val();--%>
        var url = '${pageContext.request.contextPath}/pengambilan/terima_warta_mlk?popupScan&idRujukan='+idRujukan;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=800,height=700");
    }

  </script>
<s:messages/>
<s:errors/>
<s:form beanclass="etanah.view.stripes.pengambilan.Warta_MLKActionBean">

             <div class="content">
                <fieldset class="aras1">
                    <legend>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SEK4'}">Warta Seksyen 4</c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq '831A'}">Warta Seksyen 3(1)(a)-Borang D</c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq '831B'}">Warta Borang 3(1)(b)-Borang D</c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq '831C'}">Warta Borang 3(1)(c)-Borang D</c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PB'}">Warta Penarikan Balik</c:if>
                    </legend>

                    <div class="content" align="center">
                        <p align="left">
                        <label style="text-align:right;width:20%; padding: 0em 0em">Tarikh Penerimaan :</label>
                        <s:text style="text-align:left;width:15%" name="tarikhDisampai" id="datepicker" class="datepicker" maxlength="10"/>
                            <br /><br />

                            <p align="left">
                        <label style="text-align:right;width:20%; padding: 0em 0em">Tarikh Warta :</label>
                        <s:text style="text-align:left;width:15%" name="tarikhWarta" id="datepicker2" class="datepicker" maxlength="10"/>
                            <br /><br />

                            <p align="left">
                        <label style="text-align:right;width:20%; padding: 0em 0em">No. Warta :</label>
                        <s:text style="text-align:left;width:15%" name="noWarta" id="nowarta" maxlength="10"/>
                        <br /><br />

                        <p align="left">
                        <label style="text-align:right;width:20%; padding: 0em 0em">Terdapat Kesilapan :</label>
                        <s:radio name="permohonanRujukanLuar.statusUlasan" value="Y" onclick="showUlasan();">Ya</s:radio>YA
                        <s:radio name="permohonanRujukanLuar.statusUlasan" value="T" onclick="hideUlasan();">Tidak</s:radio>TIDAK
                        <br /><br />

                        <p align="left">
                        <label style="text-align:right;width:20%; padding: 0em 0em">Jika Ada :</label>

                         <c:if test="${actionBean.ulasan eq null}"><s:textarea name="ulasan" rows="10" cols="50" id="adakesilapan"/>&nbsp;</c:if>
                         <c:if test="${actionBean.ulasan ne null}"> <s:textarea name="ulasan" rows="10" cols="50" id="adakesilapan"/></c:if>
                         <br /><br />

                         <c:if test="${simpanWarta}">
                            <p align="center">
                                <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
                                <s:button name="reset" value="Isi Semula" class="btn" onclick="test();"/>

                        </c:if>
                                 <c:if test="${simpanWarta2}">
                            <p align="center">
                                <s:button name="simpan2" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
                                <s:button name="reset" value="Isi Semula" class="btn" onclick="test();"/>

                        </c:if>

                    </div>
                </fieldset>
     </div>
     <div class="subtitle displaytag">
        <fieldset class="aras1" id="locate">
            <legend>
                Maklumat Warta Seksyen 8
            </legend>
            <p align="center"><label></label>
                <display:table class="tablecloth" name="${actionBean.permohonanRujukanLuarListD}" pagesize="20" style="width:100%" cellpadding="0" cellspacing="0" id="line">
                    <display:column title="No">${line_rowNum}</display:column>
                    <display:column property="catatan" title="Jenis" />
                    <display:column property="item" title="No. Warta" />
                    <display:column title="Tarikh Warta" style="vertical-align:top;">
                            <fmt:formatDate value="${line.tarikhLulus}" pattern="dd/MM/yyyy"/>
                        </display:column>
                    <display:column title="Tarikh Terima" style="vertical-align:top;">
                            <fmt:formatDate value="${line.tarikhDisampai}" pattern="dd/MM/yyyy"/>
                        </display:column>
                    <display:column property="ulasan" title="Ulasan" maxLength="4000" />
                     <display:column title="Hapus">
                    <div align="center">
                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeWarta('${line.idRujukan}');" />
                    </div>
                    </display:column>
                     <display:column title="Tindakan">
                                <p align="center">
                                    <c:if test="${line.dokumen.idDokumen eq ''}">
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                             onmouseover="this.style.cursor='pointer';" onclick="muatNaikForm('${line.idRujukan}');return false;" title="Muat Naik Dokumen"/>
                                        /
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png" height="30" width="30" alt="Imbas"
                                             onmouseover="this.style.cursor='pointer';" onclick="scan('${line.idRujukan}');return false;" title="Imbas Dokumen"/>
                                    </c:if>
                                    <c:if test="${line.dokumen.idDokumen ne ''}">
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                             onmouseover="this.style.cursor='pointer';" onclick="muatNaikForm('${line.idRujukan}');return false;" title="Muat Naik Dokumen"/>
                                        /
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png" height="30" width="30" alt="Imbas"
                                             onmouseover="this.style.cursor='pointer';" onclick="scan('${line.idRujukan}');return false;" title="Imbas Dokumen"/>
                                        /
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar"
                                             onclick="doViewReport('${line.dokumen.idDokumen}');" onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                                    </c:if>


                                </p>
                       </display:column>
                     </display:table>
            <br>
        </fieldset>
     </div>
     <div class="subtitle displaytag">
        <fieldset class="aras1" id="locate">
            <legend>
                Maklumat Warta Seksyen 4
            </legend>
            <p align="center"><label></label>
                <display:table class="tablecloth" name="${actionBean.permohonanRujukanLuarList}" pagesize="20" style="width:100%" cellpadding="0" cellspacing="0" id="line2">
                    <display:column title="No">${line2_rowNum}</display:column>
                    <display:column title="Id Permohonan Terdahulu" style="vertical-align:top;">
                        ${line2.permohonan.idPermohonan}
                    </display:column>
                    <display:column title="Nama Urusan" style="vertical-align:top;">
                       ${line2.permohonan.kodUrusan.nama}
                    </display:column>
                    <display:column property="catatan" title="Jenis" />
                    <display:column property="item" title="No. Warta" />
                    <display:column title="Tarikh Warta" style="vertical-align:top;">
                            <fmt:formatDate value="${line2.tarikhLulus}" pattern="dd/MM/yyyy"/>
                        </display:column>
                    <display:column title="Tarikh Terima" style="vertical-align:top;">
                            <fmt:formatDate value="${line2.tarikhDisampai}" pattern="dd/MM/yyyy"/>
                        </display:column>
                    <display:column property="ulasan" title="Ulasan" />
                    <display:column title="Tindakan">
                                <p align="center">
                                    <c:if test="${line2.dokumen.idDokumen ne ''}">
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar"
                                             onclick="doViewReport('${line2.dokumen.idDokumen}');" onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                                    </c:if>
                                </p>
                       </display:column>
                     </display:table>
            <br>
        </fieldset>
    </div>
   </s:form>
