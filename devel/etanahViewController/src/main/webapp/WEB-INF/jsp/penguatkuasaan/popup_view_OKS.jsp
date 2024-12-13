<%-- 
    Document   : popup_view_OKS
    Created on : April 6, 2011, 11:13:51 AM
    Author     : latifah.iskak
    modify by sitifariza.hanim (18042011)
modified by : ctzainal 15june 2011
--%>


<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript">
    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });
    function validateForm(){
        self.opener.refreshPageCeroboh();
        self.close();
    }
    <%-- function validateNumber(elmnt,content) {
         //if it is character, then remove it..
         if (isNaN(content)) {
             elmnt.value = removeNonNumeric(content);
             return;
         }
     }--%>

         function validateNumber(elmnt,content) {

             //if it is fullstop (.) , then remove it..
             for( var i = 0; i < content.length; i++ )
             {
                 var str = "";
                 str = content.substr( i, 1 );
                 if(str == "."){
                     elmnt.value = removeNonNumeric(content);
                     return;
                 }
             }

             //if it is character, then remove it..
             if (isNaN(content)) {
                 elmnt.value = removeNonNumeric(content);
                 return;
             }
         }

         function removeNonNumeric( strString )
         {
             var strValidCharacters = "1234567890";
             var strReturn = "";
             var strBuffer = "";
             var intIndex = 0;
             // Loop through the string
             for( intIndex = 0; intIndex < strString.length; intIndex++ )
             {
                 strBuffer = strString.substr( intIndex, 1 );
                 // Is this a number
                 if( strValidCharacters.indexOf( strBuffer ) > -1 )
                 {
                     strReturn += strBuffer;
                 }
             }
             return strReturn;
         }
         function test(f) {
             $(f).clearForm();
         }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
<s:form  beanclass="etanah.view.penguatkuasaan.MaklumatOrangDisyakiActionBean">
    <div class="subtitle">
        <s:hidden name="aduanOrangKenaSyak.idOrangKenaSyak"/>
        <fieldset class="aras1">
            <legend> Maklumat Orang Disyaki </legend>

            <p>
                <label>Nama :</label>
                ${actionBean.aduanOrangKenaSyak.nama}
            </p>

            <p>
                <label>Jenis Pengenalan :</label>
                ${actionBean.aduanOrangKenaSyak.kodJenisPengenalan.nama}
            </p>
            <p>
                <label>No.Pengenalan :</label>
                ${actionBean.aduanOrangKenaSyak.noPengenalan}
            </p>
            &nbsp; <p>
                <label>Tarikh Lahir :</label>
                <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.aduanOrangKenaSyak.tarikhlahir}"/>&nbsp;
            </p>
            <p>
                <label>Jantina :</label>
                ${actionBean.aduanOrangKenaSyak.kodJantina.nama}
            </p>
            <p>
                <label>Alamat :</label>
                ${actionBean.aduanOrangKenaSyak.alamat.alamat1}
            </p>&nbsp;
            <p>
                <label>&nbsp;</label>
                ${actionBean.aduanOrangKenaSyak.alamat.alamat2}
            </p>&nbsp;
            <p>
                <label>&nbsp;</label>
                ${actionBean.aduanOrangKenaSyak.alamat.alamat3}
            </p>&nbsp;
            <p>
                <c:choose>
                    <c:when test="${actionBean.kodNegeriUrusan eq '05'}">
                        <label> Daerah : </label>
                    </c:when>
                    <c:otherwise>
                        <label> &nbsp; </label>
                    </c:otherwise>
                </c:choose>
                ${actionBean.aduanOrangKenaSyak.alamat.alamat4}
            </p>&nbsp;
            <p>
                <label>Poskod :</label>
                ${actionBean.aduanOrangKenaSyak.alamat.poskod}
            </p>&nbsp;
            <p>
                <label>Negeri :</label>
                ${actionBean.aduanOrangKenaSyak.alamat.negeri.nama}
            </p>&nbsp; 
            <p>
                <label>Warna Kad Pengenalan :</label>
                ${actionBean.aduanOrangKenaSyak.kodWarnaKP.nama}
            </p>
            <p>
                <label>Bangsa :</label>
                ${actionBean.aduanOrangKenaSyak.kodBangsa.perihal}
            </p>
            <p>
                <label>Jenis Warganegara :</label>
                ${actionBean.aduanOrangKenaSyak.kodWarganegara.nama}
            </p>
            <p>
                <label>Pekerjaan :</label>
                ${actionBean.aduanOrangKenaSyak.pekerjaan}
            </p>
            <p>
                <label>No Telefon :</label>
                ${actionBean.aduanOrangKenaSyak.noTelefon1}
            </p>

            &nbsp; <p>
                <label>Lokasi Ditahan :</label>
                ${actionBean.aduanOrangKenaSyak.tempatOksDitahan}
            </p>
            &nbsp; <p>
                <label>Tempat Direman :</label>
                ${actionBean.aduanOrangKenaSyak.tempatDireman}
            </p>
            &nbsp; <p>
                <label>Tarikh Mula Ditahan:</label>
                <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.aduanOrangKenaSyak.tarikhDitahan}"/>&nbsp;
            </p>
            &nbsp; <p>
                <label>Tarikh Dilepaskan:</label>
                <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.aduanOrangKenaSyak.tarikhDiBebas}"/>&nbsp;
            </p>
            &nbsp; <p>
                <label>Tempoh Tahanan :</label>
                ${actionBean.aduanOrangKenaSyak.diBebas}
                <c:if test="${not empty actionBean.aduanOrangKenaSyak.diBebas}"> hari</c:if>
            </p>

            &nbsp; <p>
                <label>Disenarai Hitam :</label>
                <c:if test="${actionBean.aduanOrangKenaSyak.senaraiHitam eq 'B'}">
                    Disenarai Hitam &nbsp;
                </c:if>
                <c:if test="${actionBean.aduanOrangKenaSyak.senaraiHitam eq 'T'}">
                    Tidak Disenarai Hitam &nbsp;
                </c:if>


            </p>
            &nbsp; <p>
                <label>Tarikh Disenarai Hitam :</label>
                <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.aduanOrangKenaSyak.tarikhSenaraiHitam}"/>&nbsp;
            </p>&nbsp; 
            <p>
                <label>Keterangan :</label>
            </p>
            <c:if test="${actionBean.aduanOrangKenaSyak.keterangan ne null}">
                <table>
                    <tr>
                        <td valign="top">
                            <font size="2px;">${actionBean.aduanOrangKenaSyak.keterangan}&nbsp;</font></td>
                    </tr>
                </table>
            </c:if>
            <%--<c:if test="${actionBean.aduanOrangKenaSyak.keterangan eq null}"> Tiada Data </c:if>--%>

        </fieldset>

        <br>

        <fieldset class="aras1">
            <legend>Maklumat Majikan Orang Disyaki</legend>

            &nbsp;<p>
                <label>Nama Majikan:</label>
                ${actionBean.aduanOrangKenaSyak.namaMajikan}
            </p>&nbsp; 
            <p>
                <label>No Telefon Majikan:</label>
                ${actionBean.aduanOrangKenaSyak.noTelMajikan}
            </p>&nbsp;
            <p>
                <label>No Faks Majikan:</label>
                ${actionBean.aduanOrangKenaSyak.noFaksMajikan}
            </p>&nbsp;
            <p>
                <label>Alamat Majikan:</label>
                ${actionBean.aduanOrangKenaSyak.alamat1Majikan}
            </p>
            &nbsp; <p>
                <label>&nbsp;</label>
                ${actionBean.aduanOrangKenaSyak.alamat2Majikan}
            </p>
            &nbsp; <p>
                <label> &nbsp; </label>
                ${actionBean.aduanOrangKenaSyak.alamat3Majikan}
            </p>
            &nbsp; <p>
                <label> &nbsp; </label>
                ${actionBean.aduanOrangKenaSyak.alamat4Majikan}
            </p>
            &nbsp;<p>
                <label>Poskod :</label>
                ${actionBean.aduanOrangKenaSyak.poskodMajikan}
            </p>
            &nbsp; <p>
                <label>Negeri :</label>
                ${actionBean.aduanOrangKenaSyak.kodNegeriMajikan.nama}
            </p>
        </fieldset>

        <br>
        <fieldset class="aras1">
            <legend>Maklumat Waris Orang Disyaki</legend>
            <p>
                <label>Nama Waris :</label>
                ${actionBean.warisOrangKenaSyak.nama}
            </p>
            &nbsp;
            <p>
                <label>Hubungan :</label>
                ${actionBean.warisOrangKenaSyak.hubungan}
            </p>
            &nbsp;
            <p>
                <label>Alamat Waris :</label>
                ${actionBean.warisOrangKenaSyak.alamat1}
            </p>

            &nbsp; <p>
                <label>&nbsp;</label>
                ${actionBean.warisOrangKenaSyak.alamat2}
            </p>
            &nbsp; <p>
                <label> &nbsp; </label>
                ${actionBean.warisOrangKenaSyak.alamat3}
            </p>
            &nbsp; <p>
                <label> &nbsp; </label>
                ${actionBean.warisOrangKenaSyak.alamat4}
            </p>
            &nbsp;<p>
                <label>Poskod :</label>
                ${actionBean.warisOrangKenaSyak.poskod}
            </p>
            &nbsp; <p>
                <label>Negeri :</label>
                ${actionBean.warisOrangKenaSyak.negeri.nama}
            </p>
            &nbsp;
            <p>
                <label>Jenis Pengenalan :</label>
                ${actionBean.warisOrangKenaSyak.jenisPengenalan.nama}
            </p>
            <p>
                <label>No.Pengenalan :</label>
                ${actionBean.warisOrangKenaSyak.noPengenalan}
            </p>
            &nbsp;
            <p>
                <label>Jantina :</label>
                ${actionBean.warisOrangKenaSyak.kodJantina.nama}
            </p>
            &nbsp;
            <p>
                <label>No Telefon :</label>
                ${actionBean.warisOrangKenaSyak.noTelefon}
            </p>
            &nbsp;
            <p>
                <label>No Telefon Bimbit :</label>
                ${actionBean.warisOrangKenaSyak.noTelefonBimbit}
            </p>

        </fieldset>

        <p><label>&nbsp;</label>
            <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
        </p>
        <br>
    </div>
</s:form>