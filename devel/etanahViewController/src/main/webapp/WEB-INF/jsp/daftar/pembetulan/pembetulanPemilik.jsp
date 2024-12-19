<%-- 
    Document   : pembetulanPemilik
    Created on : May 10, 2010, 4:08:06 PM
    Author     : Huda & Zalina (ToT)
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>
<style type="text/css">
    td.s{
        font-weight:normal;
        color:black;
        text-align: left;

    }
    input, select{width:95%}
    td{
        font-size: 100% !important;
    }
    .arrow { background:transparent url(${pageContext.request.contextPath}/pub/images/arrows.png) no-repeat scroll 0px -16px; width:100%; height:16px; display:block;}
    .up { background-position:0px 0px;}
</style>
<script language="javascript">
    var globalFlag = false;
    $(document).ready( function(){
      
    <%--set focus--%>
            $('input').focus(function() {
                $(this).addClass("focus");
            });

            $('input').blur(function() {
                $(this).removeClass("focus");
            });

            $('select').focus(function() {
                $(this).addClass("focus");
            });

            $('select').blur(function() {
                $(this).removeClass("focus");
            });
  
            $('#copy').click(function(){
                if(this.checked)
                {
                    $('#alamatsurat1').val($('#alamat1').val());
                    $('#alamatsurat2').val($('#alamat2').val());
                    $('#alamatsurat3').val($('#alamat3').val());
                    $('#alamatsurat4').val($('#alamat4').val());
                    $('#poskodsurat').val($('#poskod').val());
                    $('#negerisurat').val($('#negeri').val());

                }
            });

       <%--     $('#poskod').keyup(function(){

                poskodValidate('poskod');
        
            });

            $('#poskodSurat').keyup(function(){

                poskodValidate('poskodSurat');

            });--%>

        });


 function getDIVID(idName)
 {
poskodValidate(idName);
 }

  function poskodValidate(idPoskod)
        {
    <%--alert(idPoskod);--%>
            var numberRegex = /^[+-]?\d+(\.\d+)?([eE][+-]?\d+)?$/;
            var str = $('#'+idPoskod).val();
            if(str.length == 5){
                if(!numberRegex.test(str)) {
    <%--alert('Sila');--%>
                                    $('.'+idPoskod).html("*Sila betulkan kesalahan");
                                    $('#'+idPoskod).focus();
                                }
                                if(numberRegex.test(str)) {
    <%--alert(globalFlag);--%>
                                    $('.'+idPoskod).html("");
                                    globalFlag = true;
                                }
                            }
                        }

    function save(event, f, idH, idPihak)
     {



                var q = $(f).formSerialize();
                var url = f.action + '?' + event+'&idH='+idH+'&idPihak='+idPihak ;

                 $.post(url,q,
        function(data){
            $('#page_div').html(data);
           <%-- alert(data);--%>
        },'html');

                  }
    
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form name="form1" beanclass="etanah.view.stripes.nota.pembetulanActionBean">
<div id="page_div">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Pemilik
            </legend>

            <p style="color:red">
                *Sila isi yang berkenaan sahaja untuk pembetulan.
            </p>
            <div align="center">

                <table class="tablecloth" width="70%">
                    <tr><th colspan="3">Butiran Pihak Berkepentingan</th></tr>
                    <tr><th>Perkara</th><th>Maklumat Lama</th><th>Maklumat Baru</th></tr>


                    <tr><td>Nama :</td><td class="s">${actionBean.pihak.nama}</td>
                        <td class="s">
                            <s:text name="nama"/>
                        </td></tr>

                    <tr><td>Jenis Pengenalan :</td><td class="s">${actionBean.pihak.jenisPengenalan.nama}</td>
                        <td class="s">
                            <s:select name="jenisPengenalan" id="" value="kod" >
                                <s:option value="">Pilih ...</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod" />
                            </s:select>
                        </td></tr>

                    <tr><td>No. Pengenalan :</td><td class="s">${actionBean.pihak.noPengenalan}</td>
                        <td class="s">
                            <s:text name="noPengenalan"/>
                        </td></tr>

                   <tr><td>Bangsa :</td><td class="s">${actionBean.pihak.bangsa.nama}</td>
                        <td class="s">
                            <s:select name="bangsa" id="pemilikBangsa" value="kod">
                                <s:option value="">Pilih ...</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodBangsa}" label="nama" value="kod" />
                            </s:select>
                        </td></tr>

                    <tr><td>Jantina :</td><td class="s">
                            <c:if test="${actionBean.pihak.kodJantina eq 1}">
                                Lelaki
                            </c:if>
                            <c:if test="${actionBean.pihak.kodJantina eq 2}">
                                Perempuan
                            </c:if>
                            <c:if test="${actionBean.pihak.kodJantina eq 3}">
                                Tidak Dikenalpasti
                            </c:if>
                            <c:if test="${actionBean.pihak.kodJantina eq 0}">
                                Tidak Berkenaan
                            </c:if>

                        </td>
                        <td class="s">
                            <s:select name="jantina" id="pemilikJantina" value="kod">
                                <s:option value="">Pilih ...</s:option>
                                <s:option value="1">Lelaki</s:option>
                                <s:option value="2">Perempuan</s:option>
                                <s:option value="3">Tidak Dikenalpasti</s:option>
                                <s:option value="0">Tidak Berkenaan</s:option>
                            </s:select>
                        </td></tr>

                    <tr><td>Warganegara :</td><td class="s">${actionBean.pihak.wargaNegara.nama}</td>
                        <td class="s">
                            <s:select name="warganegara" id="pemilikWarganegara" value="kod">
                                <s:option value="">Pilih ...</s:option>
                                <s:options-collection collection="${listUtil.senaraiWarganegara}" label="nama" value="kod" />
                            </s:select>
                        </td></tr>
                    <tr><td>Jenis Pihak Berkepentingan :</td><td class="s">${actionBean.hpBerkepentingan.jenis.nama}</td>
                        <td class="s">
                            <s:select name="jenisPB" value="kod">
                                <s:option value="">Pilih ...</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodJenisPihakBerkepentingan}" label="nama" value="kod" />
                            </s:select>
                        </td></tr>
                    <tr><td>Kumpulan Pihak Berkepentingan :</td><td class="s">&nbsp;</td>
                        <td class="s">
                            <s:select name="kumpulanPB" value="kod">
                                <s:option value="">Pilih ...</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodJenisPihakBerkepentingan}" label="nama" value="kod" />
                            </s:select>
                        </td></tr>
                    <tr><td>Ruj. Surat Amanah :</td><td class="s">&nbsp;</td>
                        <td class="s">
                            <s:text name="noSuratAmanah"/>
                        </td></tr>
                    <tr><td>Penyebut / Pembilang :</td><td class="s">${actionBean.hpBerkepentingan.syerPenyebut}/${actionBean.hpBerkepentingan.syerPembilang}</td>
                        <td class="s">
                            <s:text name="penyebut" style="width:46% !important;"/> / <s:text name="pembilang" style="width:46% !important;"/>
                        </td></tr>
                
                    <%--start alamat--%>
                    <tr><td colspan="3">&nbsp;</td></tr>
                    <tr><th colspan="3">Alamat Pihak Berkepentingan</th></tr>
                    <tr><th colspan="3">Alamat Tetap</th></tr>
                    <tr><td>Alamat :</td><td class="s">${actionBean.hpBerkepentingan.pihak.alamat1}</td>
                        <td class="s">
                            <s:text name="alamat1" id="alamat1"/>
                        </td></tr>

                    <tr><td>&nbsp;</td><td class="s">${actionBean.hpBerkepentingan.pihak.alamat2}</td>
                        <td class="s">
                            <s:text name="alamat2" id="alamat2"/>
                        </td></tr>

                    <tr><td>&nbsp;</td><td class="s">${actionBean.hpBerkepentingan.pihak.alamat3}</td>
                        <td class="s">
                            <s:text name="alamat3" id="alamat3"/>
                        </td></tr>

                    <tr><td>&nbsp;</td><td class="s">${actionBean.hpBerkepentingan.pihak.alamat4}</td>
                        <td class="s">
                            <s:text name="alamat4" id="alamat4"/>
                        </td></tr>

                    <tr><td>Poskod :</td><td class="s">${actionBean.hpBerkepentingan.pihak.poskod}</td>
                        <td class="s">
                            <s:text name="poskod" id="poskod" maxlength="5" formatType="number" onkeyup="getDIVID(this.id);"/>
                            <div class="poskod" style="color:red; font-size:8pt; margin:0px;"></div>
                        </td></tr>

                    <tr><td>Negeri :</td><td class="s">${actionBean.hpBerkepentingan.pihak.negeri.nama}</td>
                        <td class="s">
                            <s:select name="negeri" id="negeri" value="kod">
                                <s:option value="">Pilih ...</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                            </s:select>
                        </td></tr>

                    <tr><th colspan="3">Alamat Surat-menyurat</th></tr>
                    <tr><td style="color:red; text-align:right" colspan="3">*Sama seperti alamat tetap &nbsp;<s:checkbox id="copy" name="copy" title="Guna alamat tetap" style="width:5% !important;" /></td>
                    <tr><td>Alamat:</td><td class="s">${actionBean.hpBerkepentingan.pihak.suratAlamat1}</td>
                        <td class="s">
                            <s:text name="alamatSurat1" id="alamatsurat1"/>
                        </td></tr>

                    <tr><td>&nbsp;</td><td class="s">${actionBean.hpBerkepentingan.pihak.suratAlamat2}</td>
                        <td class="s">
                            <s:text name="alamatSurat2" id="alamatsurat2"/>
                        </td></tr>

                    <tr><td>&nbsp;</td><td class="s">${actionBean.hpBerkepentingan.pihak.suratAlamat3}</td>
                        <td class="s">
                            <s:text name="alamatSurat3" id="alamatsurat3"/>
                        </td></tr>

                    <tr><td>&nbsp;</td><td class="s">${actionBean.hpBerkepentingan.pihak.suratAlamat4}</td>
                        <td class="s">
                            <s:text name="alamatSurat4" id="alamatsurat4"/>
                        </td></tr>

                    <tr><td>Poskod :</td><td class="s">${actionBean.hpBerkepentingan.pihak.suratPoskod}</td>
                        <td class="s">
                            <s:text name="poskodSurat" id="poskodsurat" size="5" maxlength="5" onkeyup="getDIVID(this.id);"/>
                             <div class="poskodsurat" style="color:red; font-size:8pt; margin:0px;"></div>
                        </td></tr>

                    <tr><td>Negeri :</td><td class="s">${actionBean.hpBerkepentingan.pihak.suratNegeri.nama}</td>
                        <td class="s">
                            <s:select name="negeriSurat" id="negerisurat" value="kod">
                                <s:option value="">Pilih ...</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod"  />
                            </s:select>
                        </td></tr>
                    <tr><td colspan="3">
                            <div align="center">
                                <s:button name="saveBetulPB" value="Simpan" class="btn" onclick="save(this.name, this.form, '${actionBean.hakmilik.idHakmilik}', '${actionBean.pihak.idPihak}')"/>
                                <s:button name="tutup" value="Tutup" class="btn" onclick="javascript:self.close()"/>
                            </div>
                        </td></tr>
                </table>


                <br/>

                <%--<s:button class="btn" onclick="doSubmit(this.form, this.name, 'page_div');" name="simpanMaklumatPihakBerkepentingan" value="Kemaskini"/>
                <s:button class="btn" onclick="doSubmit(this.form, this.name, 'page_div');" name="simpanPihakBerkepentingan" value="Simpan"/>--%>

            </div>

        </fieldset>
    </div>
</div>
</s:form>