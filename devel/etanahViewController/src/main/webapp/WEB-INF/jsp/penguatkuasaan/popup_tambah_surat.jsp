<%-- 
    Document   : popup_tambah_surat
    Created on : Nov 11, 2011, 11:24:21 AM
    Author     : latifah.iskak
--%>


<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
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
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<style type="text/css">
    #rujukan {
        float:right;
        margin-right:11.0em;
    }

    #alamat {
        float:left;
        margin-left:-27.0em;
    }

    #kandungan {
        float:left;
        margin-left:8.0em;
        margin-top: 29px;
    }

    #up {
        float:left;
        margin-left:26.1em;
    }

    #faks {
        float:right;
        margin-left: 137px;
        margin-right: 40px;
        /*margin-top: -24px;*/
    }

    #viewFaks {
        float:right;
        margin-top: -19px;
        margin-left: 572px;
    }

    #oleh {
        float:left;
        margin-left:-17.0em;
    }

</style>
<script type="text/javascript">
    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });

    function save(event, f){

        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.opener.refreshPageSuratSaksi();
            self.close();
        },'html');

    }

    function validateForm(){
        if($('#poskodSidang').val()=="")
        {
            alert('Sila isikan poskod terlebih dahulu');
            $('#poskodSidang').focus();
            return false;
        }
        
        if($('#negeriSidang').val()=="")
        {
            alert('Sila pilih negeri terlebih dahulu');
            $('#negeriSidang').focus();
            return false;
        }
        
        if($('#wakilSidang').val()=="")
        {
            alert('Sila isikan u.p (untuk perhatian) terlebih dahulu');
            $('#wakilSidang').focus();
            return false;
        }
        
        if($('#noFaks').val()=="")
        {
            alert('Sila isikan no.faks terlebih dahulu');
            $('#noFaks').focus();
            return false;
        }
        
        if($('#tajuk').val()=="")
        {
            alert('Sila isikan lokasi mahkamah majistret terlebih dahulu');
            $('#tajuk').focus();
            return false;
        }
        if($('#noRujukan').val()=="")
        {
            alert('Sila isikan no.saman terlebih dahulu');
            $('#noRujukan').focus();
            return false;
        }
        if($('#penyediaSidang').val()=="")
        {
            alert('Sila pilih oks terlebih dahulu');
            $('#penyediaSidang').focus();
            return false;
        }
        if($('#tarikhSidang').val()=="")
        {
            alert('Sila pilih tarikh bicara terlebih dahulu');
            $('#tarikhSidang').focus();
            return false;
        }
        
        if($('#tempatKandungan').val()=="")
        {
            alert('Sila isikan lokasi kehadiran terlebih dahulu');
            $('#tempatKandungan').focus();
            return false;
        }
        if($('#tarikhKandungan').val()=="")
        {
            alert('Sila pilih tarikh kehadiran terlebih dahulu');
            $('#tarikhKandungan').focus();
            return false;
        }
        return true;
    }

    function test(f) {
        $(f).clearForm();
    }


    function convertText(r,t){
        var i = r.value;
        document.getElementById(t).value=i.toUpperCase();
    }


    function validateNumber(elmnt,content) {
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


    function limitText(limitField, limitNum) {
        if (limitField.value.length > limitNum) {
            limitField.value = limitField.value.substring(0, limitNum);
        }
    }

    
    function convertText(r,t){
        var i = r.value;
        document.getElementById(t).value=i.toUpperCase();
    }
    
    function validateTelLength(value){
        var plength = value.length;
        if(plength < 7){
            alert('No. Telefon yang dimasukkan salah.');
            $('#noFaks').val("");
            $('#noFaks').focus();
        }
    }


 
</script>
<s:form beanclass="etanah.view.penguatkuasaan.SuratPanggilanSaksiActionBean">
    <s:messages />

    <c:if test="${edit}">
        <div class="subtitle">
            <fieldset class="aras1">
                <div class="content">

                    <div id="rujukan">
                        <p>
                            <label>Rujukan Kami : </label>
                            ${actionBean.permohonan.idPermohonan}&nbsp;<br>
                            <label>Tarikh : </label>
                            <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.currentDate}"/>&nbsp;

                        </p>
                    </div>

                    <div id="alamat">
                        <p>
                            <label>Alamat : </label>
                            <s:text name="alamatSidang1" id="alamatSidang1" value="Majistret" readonly="true" size="50"/>
                        </p>
                        <p>
                            <label>&nbsp;</label>
                            <s:text name="alamatSidang2" id="alamatSidang2" value="Mahkamah Majistret" size="50" maxlength="60"/>
                        </p>
                        <p>
                            <label>&nbsp;</label>
                            <s:text name="alamatSidang3" id="alamatSidang3" size="50" maxlength="60"/>
                        </p>
                        <p>
                            <label>Poskod : </label>
                            <s:text name="poskodSidang" id="poskodSidang" onkeyup="validateNumber(this,this.value);" maxlength="5"/>
                        </p>
                        <p>
                            <label>Negeri :</label>
                            <s:select name="negeriSidang">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                            </s:select>&nbsp;
                        </p>

                        <p id="up">
                            <label>(u.p. <s:text name="wakilSidang" id="wakilSidang" size="50" maxlength="250"/> ) </label>
                        </p>


                        <div id="faks">
                            <font style="color: #003194;font-size: 13px;font-weight: bold;">No. Faks :</font>
                            <s:text name="noFaks" id="noFaks" maxlength="10" onkeyup="validateNumber(this,this.value);" onchange="validateTelLength(this.value);"/> 
                        </div>
                    </div>


                    <br>
                    <div id="kandungan">
                        <p>
                            Tuan,
                            <br><br>

                            <b>PERMOHONAN PANGGILAN SAKSI</b><br>

                        </p>
                        <p>
                            <b>MAHKAMAH MAJISTRET <s:text name="tajuk" id="tajuk" onchange="this.value=this.value.toUpperCase();"/>&nbsp; MELAKA</b>
                        </p>
                        <p>
                            <b>SAMAN NO. </b><s:text name="noRujukan" id="noRujukan"/>&nbsp;
                        </p>
                        <p>
                            <b>OKS : </b> 
                            <s:select name="penyediaSidang">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${actionBean.senaraiOksIp}" label="nama" value="nama" />
                            </s:select>&nbsp;&nbsp;
                        </p>
                        <p>
                            <b>SEK : 426 KANUN TANAH NEGARA 1965 &nbsp;&nbsp;</b> 
                        </p>
                        <p>
                            <b>REPORT NO. : </b> 
                            <s:select name="noLaporan">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${actionBean.senaraiOperasiAgensi}" label="noRujukan" value="noRujukan" />
                            </s:select>&nbsp;&nbsp;
                        </p>
                        <p>
                            <b>TARIKH BICARA : </b> 
                            <s:text name="tarikhSidang" id="tarikhSidang" class="datepicker" formatPattern="dd/MM/yyyy" /> 
                        </p>

                        <br>
                        <p>
                            Dengan hormatnya perkara di atas adalah dirujuk.
                        </p>
                        <br>

                        <p>
                            2.&nbsp;&nbsp;&nbsp;Pohon pihak tuan mengeluarkan panggilan saksi kepada saksi-saksi sepertimana di bawah ini <br> untuk hadir di Mahkamah Majistret <s:text name="tempatKandungan" id="tempatKandungan"/> Melaka pada 
                            <s:text name="tarikhKandungan" id="tarikhKandungan" class="datepicker" formatPattern="dd/MM/yyyy" />  jam    
                            <s:select name="jam" id="jam">
                                <s:option value=""> Jam </s:option>
                                <c:forEach var="jam" begin="1" end="12">
                                    <s:option value="${jam}">${jam}</s:option>
                                </c:forEach>
                            </s:select>
                            <s:select name="minit" id="minit">
                                <s:option value=""> Minit </s:option>
                                <c:forEach var="minit" begin="00" end="59" >
                                    <c:choose>
                                        <c:when test="${minit > 9}"><s:option value="${minit}">${minit}</s:option></c:when>
                                        <c:otherwise><s:option value="0${minit}">0${minit}</s:option></c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </s:select>
                            <s:select name="ampm" id="ampm" style="width:80px">
                                <s:option value="">Pilih</s:option>
                                <s:option value="AM">PAGI</s:option>
                                <s:option value="PM">PETANG</s:option>
                            </s:select>.
                        </p>
                        <br>
                        <p>
                            3.&nbsp;&nbsp;&nbsp;Senarai saksi adalah seperti di Lampiran A.
                        </p>
                        <br>
                        <p>
                            4.&nbsp;&nbsp;&nbsp;Kerjasama serta tindakan segera daripada pihak tuan berkenaan hal ini amatlah dihargai
                        </p>
                        <br>
                        <p>
                            Sekian. Terima Kasih<br><br>
                        </p>


                    </div>
                    <br><br>
                    <div id="oleh">
                        <p>
                            <label>Ditandatangan Oleh :</label>
                            <s:select name="diTandatanganOleh">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${actionBean.senaraiPengguna}" label="nama" value="idPengguna" />
                            </s:select>
                        </p>
                    </div>



                </div>
                <br/><br/>

            </fieldset>
        </div>

        <p align="center">
            <s:hidden name="idKertas"/>
            <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
            <s:button class="btn" onclick="if(validateForm())save(this.name,this.form);" name="simpan" value="Simpan"/>
            <s:button name="tutup" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>

        </p>
    </c:if>

    <c:if test="${view}">
        <div class="subtitle">
            <fieldset class="aras1">
                <div class="content">

                    <div id="rujukan">
                        <p>
                            <label>Rujukan Kami : </label>
                            ${actionBean.permohonan.idPermohonan}&nbsp;<br>
                            <label>Tarikh : </label>
                            <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.currentDate}"/>&nbsp;

                        </p>
                    </div>

                    <div id="alamat">
                        <p>
                            <label>&nbsp;</label>
                            ${actionBean.kertas.sidangAlamat1}&nbsp;
                        </p>
                        <p>
                            <label>&nbsp;</label>
                            ${actionBean.kertas.sidangAlamat2}&nbsp;
                        </p>
                        <p>
                            <label>&nbsp;</label>
                            ${actionBean.kertas.sidangAlamat3}&nbsp;
                        </p>
                        <p>
                            <label>&nbsp;</label>
                            ${actionBean.kertas.sidangPoskod}&nbsp;
                        </p>
                        <p>
                            <label>&nbsp;</label>
                            ${actionBean.kertas.sidangNegeri.nama}&nbsp;
                        </p>

                        <p>
                            <label>&nbsp;</label>
                            (u.p. ${actionBean.kertas.wakilSidang})
                        </p>
                        <div id="viewFaks">
                            <p>
                                <label>No.Faks :</label>
                                ${actionBean.kertas.noFaks}
                            </p>
                        </div>
                    </div>


                    <br>
                    <div id="kandungan">
                        <p>
                            Tuan,
                            <br><br>

                            <b>PERMOHONAN PANGGILAN SAKSI</b><br>

                        </p>
                        <p>
                            <b> ${actionBean.kertas.tajuk}&nbsp;&nbsp;</b>
                        </p>
                        <p>
                            <b>SAMAN NO. </b> ${actionBean.kertas.nomborRujukan}&nbsp;
                        </p>
                        <p>
                            <b>OKS : </b> 
                            ${actionBean.kertas.penyediaSidang}&nbsp;&nbsp;
                        </p>
                        <p>
                            <b>SEK : 426 KANUN TANAH NEGARA 1965 &nbsp;&nbsp;</b> 
                        </p>
                        <p>
                            <b>REPORT NO. : </b> 
                            ${actionBean.kertas.noLaporan}&nbsp;&nbsp;
                        </p>
                        <p>
                            <b>TARIKH BICARA : </b> 
                            <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.kertas.tarikhSidang}"/>&nbsp;
                        </p>

                        <br>
                        <p>
                            Dengan hormatnya perkara di atas adalah dirujuk.
                        </p>
                        <br>

                        <p>
                            2.&nbsp;&nbsp;&nbsp;${actionBean.senaraiKertas[0].kandungan}
                        </p>
                        <br>
                        <p>
                            3.&nbsp;&nbsp;&nbsp;Senarai saksi adalah seperti di Lampiran A.
                        </p>
                        <br>
                        <p>
                            4.&nbsp;&nbsp;&nbsp;Kerjasama serta tindakan segera daripada pihak tuan berkenaan hal ini amatlah dihargai
                        </p>
                        <br>
                        <p>
                            Sekian. Terima Kasih<br><br>
                        </p>
                    </div>

                    <div id="oleh">
                        <p>
                            <label>Ditandatangan Oleh :</label>
                            <s:select name="diTandatanganOleh" disabled="true">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${actionBean.senaraiPengguna}" label="nama" value="idPengguna" />
                            </s:select>
                        </p>
                    </div>




                </div>
                <br/><br/>

            </fieldset>
        </div>

        <p align="center">
            <s:button name="tutup" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
        </p>
    </c:if>



</s:form>
