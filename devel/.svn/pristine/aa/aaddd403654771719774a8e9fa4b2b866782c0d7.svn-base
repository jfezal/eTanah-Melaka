<%-- 
    Document   : tambah_latarbelakang_ibubapa_pemohon
    Created on : May 19, 2010, 6:19:31 PM
    Author     : sitifariza.hanim
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
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
<script type="text/javascript">

    $(document).ready( function() {
        var len = $(".daerah").length;
        for (var i=0; i<=len; i++){
            $('.hakmilik'+i).click( function() {
                window.open("${pageContext.request.contextPath}/hakmilik?hakmilikDetail&idHakmilik="+$(this).text(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
            });
        }
    });

    function addPemohon(){
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon?showMaklumatAnak", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=710,height=400,scrollbars=yes");
    }
<%--
    function removePemohon(val){
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pelupusan/maklumat_pemohon?deletePemohon&idPemohon='+val;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }

    function kemaskini(i){
        var d = $('.x'+i).val();
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon?showEditPemohon&idPihak="+d, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
    }--%>

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<div class="subtitle" >
    <s:form beanclass="etanah.view.stripes.pelupusan.MaklumatPemohonActionBean">
            <fieldset class="aras1">
                <legend>Kemasukan Maklumat Bapa</legend>
                <br/>
                        <p>
                            <label for="nama"><font color="red">*</font>Nama Bapa :</label>
                            <s:text name="namaBapa" id="namaBapa" size="40"/>
                        </p>
                        <p>
                            <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                            <s:select name="pihak.jenisPengenalan.kod" onchange="javaScript:changeHideSuku(this.options[selectedIndex].text)">
                                <s:option value="0">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                        <p>
                            <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                            <s:text name="noPengenalan" id="kp" size="40"/>
                        </p>
                         <p>
                            <label>Tarikh Lahir :</label>
                            <s:text name="tarikhLahirBapa" size="40" id="datepicker" class="datepicker"/>
                        </p>
                        <p>
                            <label>Tempat Lahir :</label>
                            <s:text name="tempatLahirBapa" size="40"/>
                        </p>
                        <p>
                        <label>Bangsa :</label>
                        <s:select name="pihak.bangsa.kod" style="width:200px">
                            <s:option>Sila Pilih</s:option>
                            <s:options-collection collection="${actionBean.senaraiKodBangsa}" label="nama" value="kod" />
                        </s:select>
                        </p>
                        <p>
                        <label>Kewarganegaraan :</label>
                        <s:select name="pihak.wargaNegara.kod" style="width:200px">
                            <s:option value="0">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
                        </s:select>
                        </p>
                        <p>
                            <label>Pekerjaan :</label>
                            <s:text name="pekerjaanBapa" size="40"/>
                        </p>
                        <p>
                            <label for="alamat">Alamat Majikan :</label>
                            <s:text name="alamat1" size="40" id="alamat1"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="alamat2" size="40" id="alamat2"/>
                        </p>

                        <p>
                            <label> &nbsp;</label>
                            <s:text name="alamat3" size="40" id="alamat3"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="alamat4" size="40" id="alamat4"/>
                        </p>
                        <p>
                            <label>Poskod :</label>
                            <s:text name="poskod" size="40" maxlength="5" id="poskod" onkeyup="validateNumber(this,this.value);"/>
                        </p>

                        <p>
                            <label for="Negeri">Negeri :</label>
                            <s:select name="pihak.negeri.kod" id="negeri">
                                <s:option value="0">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                        <legend>(Jika telah meninggal dunia)</legend>
                        <p>
                            <label> Tarikh meninggal dunia</label>
                            <s:text name="tarikhMeninggalDunia" size="40" id="tarikhMeninggalDunia"/>
                        </p>
                        <p>
                            <label> Alamat Terakhir</label>
                            <s:text name="alamat1" size="40" id="alamat3"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="alamat2" size="40" id="alamat2"/>
                        </p>

                        <p>
                            <label> &nbsp;</label>
                            <s:text name="alamat3" size="40" id="alamat3"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="alamat4" size="40" id="alamat4"/>
                        </p>
                        <p>
                            <label>Poskod :</label>
                            <s:text name="poskod" size="40" maxlength="5" id="poskod" onkeyup="validateNumber(this,this.value);"/>
                        </p>

                        <p>
                            <label for="Negeri">Negeri :</label>
                            <s:select name="pihak.negeri.kod" id="negeri">
                                <s:option value="0">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                            </s:select>
                        </p>

                         <legend>Kemasukan Maklumat Ibu</legend>
                <br/>
                        <p>
                            <label for="nama"><font color="red">*</font>Nama Ibu :</label>
                            <s:text name="namaIbu" id="namaIbu" size="40"/>
                        </p>
                        <p>
                            <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                            <s:select name="pihak.jenisPengenalan.kod" onchange="javaScript:changeHideSuku(this.options[selectedIndex].text)">
                                <s:option value="0">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                        <p>
                            <label for="No Pengenalan"><font color="red">*</font>Nombor Pengenalan :</label>
                            <s:text name="noPengenalan" id="kp" size="40"/>
                        </p>
                         <p>
                            <label>Tarikh Lahir :</label>
                            <s:text name="tarikhLahirIbu" size="40" id="datepicker" class="datepicker"/>
                        </p>
                        <p>
                            <label>Tempat Lahir :</label>
                            <s:text name="tempatLahirIbu" size="40"/>
                        </p>
                        <p>
                        <label>Bangsa :</label>
                        <s:select name="pihak.bangsa.kod" style="width:200px">
                            <s:option>Sila Pilih</s:option>
                            <s:options-collection collection="${actionBean.senaraiKodBangsa}" label="nama" value="kod" />
                        </s:select>
                        </p>
                        <p>
                        <label>Kewarganegaraan :</label>
                        <s:select name="pihak.wargaNegara.kod" style="width:200px">
                            <s:option value="0">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod"/>
                        </s:select>
                        </p>
                        <p>
                            <label>Pekerjaan :</label>
                            <s:text name="pekerjaanIbu" size="40"/>
                        </p>
                        <p>
                            <label for="alamat">Alamat Majikan :</label>
                            <s:text name="alamat1" size="40" id="alamat1"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="alamat2" size="40" id="alamat2"/>
                        </p>

                        <p>
                            <label> &nbsp;</label>
                            <s:text name="alamat3" size="40" id="alamat3"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="alamat4" size="40" id="alamat4"/>
                        </p>
                        <p>
                            <label>Poskod :</label>
                            <s:text name="poskod" size="40" maxlength="5" id="poskod" onkeyup="validateNumber(this,this.value);"/>
                        </p>

                        <p>
                            <label for="Negeri">Negeri :</label>
                            <s:select name="pihak.negeri.kod" id="negeri">
                                <s:option value="0">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                        <legend>(Jika telah meninggal dunia)</legend>
                        <p>
                            <label> Tarikh meninggal dunia</label>
                            <s:text name="tarikhMeninggalDunia" size="40" id="tarikhMeninggalDunia"/>
                        </p>
                        <p>
                            <label> Alamat Terakhir</label>
                            <s:text name="alamat1" size="40" id="alamat3"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="alamat2" size="40" id="alamat2"/>
                        </p>

                        <p>
                            <label> &nbsp;</label>
                            <s:text name="alamat3" size="40" id="alamat3"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="alamat4" size="40" id="alamat4"/>
                        </p>
                        <p>
                            <label>Poskod :</label>
                            <s:text name="poskod" size="40" maxlength="5" id="poskod" onkeyup="validateNumber(this,this.value);"/>
                        </p>

                        <p>
                            <label for="Negeri">Negeri :</label>
                            <s:select name="pihak.negeri.kod" id="negeri">
                                <s:option value="0">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                        <p><br/>
                            <label>&nbsp;</label>
                            <s:button name="simpanMaklumatIbuBapa" id="simpan" value="Simpan" class="btn" onclick="savePemohon(this.name, this.form);"/>
                            <s:button name="" id="seterusnya" value="Seterusnya" class="btn" onclick="addPemohon();"/>
                            <%--<s:button class="btn" value="Next" name="new" id="new" onclick="addPemohon();"/>&nbsp;--%>
                            <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                        </p>
                        <br>
            </fieldset>
    </s:form>
</div>


