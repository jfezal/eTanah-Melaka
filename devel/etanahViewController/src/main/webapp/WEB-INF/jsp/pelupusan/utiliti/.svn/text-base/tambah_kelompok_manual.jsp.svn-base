<%-- 
    Document   : tambah_kelompok
    Created on : Jul 29, 2013, 12:35:03 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
    
    $(document).ready( function(){
    <c:if test="${actionBean.save}">
            self.close();
            opener.refreshKelompok();
    </c:if>
        });
    
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
        function filterKodSeksyen() {
            var kodBPM = $("#mpb").val();
            //            alert(katTanah);
            //            alert(kodBPM);
            //            NoPrompt();
        
            $.post('${pageContext.request.contextPath}/pelupusan/permohonan_berkelompok?senaraiKodSeksyen&kodBPM=' + kodBPM,
            function(data) {
                if (data != '') {
                    $('#partialKodSeksyen').html(data);
                    //                    $.unblockUI();
                }
            }, 'html');

        }
        
        function filterKodGunaTanah() {
            var katTanah = $("#_kodKatTanah").val();
            //            alert(katTanah);
//            NoPrompt();
        
            $.post('${pageContext.request.contextPath}/pelupusan/permohonan_berkelompok?senaraiKodGunaTanahByKatTanah&kodKatTanah=' + katTanah,
            function(data) {
                if (data != '') {
                    $('#partialKodGunaTanah').html(data);
                    //                    $.unblockUI();
                }
            }, 'html');

        }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pelupusan.CarianPermohonanBerkelompokActionBean" name ="tambahKelompok" id ="tambahKelompok">
    <s:messages/>
    <s:errors/>
    <div class ="subtitle">  
        <fieldset class="aras1">
            <legend>
                Kemasukan Permohonan Berkelompok
            </legend>
            <table class="subtitle" align="center" border="0">
                <tr>
                    <td>
                        Urusan Pelupusan 
                    </td>
                    <td>:</td>                            
                    <td>
                        <s:select name="kodUrusan" id="kodUrusan">
                            <s:option value="0">--Sila Pilih--</s:option>
                            <s:options-collection collection="${listUtil.senaraiUrusanPelupusan}" label="nama" value="kod"/>
                        </s:select>
                    </td>
                </tr>
                <tr>
                    <td>
                        Tujuan Permohonan
                    </td>
                    <td>:</td> 
                    <td>
                        <s:textarea name="sebab" rows="5" id="tujuan" cols="50"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Jenis Kelompok
                    </td>
                    <td>:</td> 
                    <td>
                        <s:radio name="jenisKelompok" id="jenisKelompok" value="K"/> Berkelompok
                    </td>
                </tr>
                <tr>
                    <td>
                        Bilangan Plot
                    </td>
                    <td>:</td> 
                    <td>
                        <s:text name="noLot" id="noLot" size="10" maxlength="4" onkeyup="validateNumber(this,this.value);"/>
                    </td>
                </tr>
                <tr>
                    <td>Mukim/Pekan/Bandar</td>
                    <td>:</td>
                    <td>
                        <s:select name="bandarPekanMukimBaru.kod" id="mpb" onchange="filterKodSeksyen();">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${actionBean.senaraiKodBPM}" label="nama" value="kod" />
                        </s:select>
                    </td>
                </tr>
                <tr>
                    <td>Seksyen</td>
                    <td>:</td>
                    <td id="partialKodSeksyen"></td>
                </tr>
                <tr>
                    <td>Kategori</td>
                    <td>:</td>
                    <td> 
                        <s:select name="kategoriTanahBaru.kod" id="_kodKatTanah" onchange="filterKodGunaTanah();"> 
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod" />
                        </s:select>
                    </td>
                </tr>
                <tr>
                    <td>Kegunaan Tanah</td>
                    <td>:</td>
                    <td id="partialKodGunaTanah"></td>
                </tr>
                <tr align="center">
                    <td colspan="3">
                        <s:submit name="simpanKelompok" value="Simpan Kelompok" class="btn"/>
                        <s:button name = "reset" class="btn" value ="Isi Semula" onclick= "clearText('tambahKelompok')" />
                        <s:button name = "tutup" class="btn" value ="Tutup" onclick= "self.close();" />
                    </td>
                </tr>
            </table>
        </fieldset>
    </div>                     
</s:form>


