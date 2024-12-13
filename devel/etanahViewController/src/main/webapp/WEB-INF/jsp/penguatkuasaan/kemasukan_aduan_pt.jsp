<%--
    Document   : kemasukan_aduan_pt
    Created on : May 18, 2014, 02:33:49 PM
    Author     : latifah.iskak
--%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>


<script type="text/javascript">
    
    $(document).ready(function() {

        jenisPengenalan();

    });

    function limitText(limitField, limitNum) {
        if (limitField.value.length > limitNum) {
            limitField.value = limitField.value.substring(0, limitNum);
        }
    }

    function jenisPengenalan(){
        if($('#pengenalan').val() == 'B'){
            document.getElementById("noPengenalanBaru").style.visibility = 'visible';
            document.getElementById("noPengenalanBaru").style.display = '';
            $('#noPengenalanLain').hide();

        }else{
            $('#noPengenalanLain').show();
            document.getElementById("noPengenalanBaru").style.visibility = 'hidden';
            document.getElementById("noPengenalanBaru").style.display = 'none';

        }
    }
    
    function validateForm(){
        
        if($('#cara').val()=="")
        {
            alert('Sila Pilih Cara Aduan');
            $('#cara').focus();
            return false;
        }
        if($('#sebab').val()=="")
        {
            alert('Sila isi Ringkasan Aduan');
            $('#sebab').focus();
            return false;
        }
        if($('#kodBpm').val()=="")
        {
            alert('Sila plih bandar/pekan/mukim');
            $('#kodBpm').focus();
            return false;
        }
        
        if($('#lokasi').val()=="")
        {
            alert('Sila masukkan lokasi');
            $('#lokasi').focus();
            return false;
        }
        return true;
    }



</script>

<s:form beanclass="etanah.view.penguatkuasaan.SenaraiAduanActionBean" name="form1">
    <s:messages/>
    <s:errors/>
    <div class="content">
        <fieldset class="aras1">
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>Tempat Aduan</legend>
                    <s:hidden name="kodNegeri" id="kodNegeriAduan"/>
                    <div class="instr-fieldset">
                        <font color="red">PERINGATAN:</font>Sila Masukkan Maklumat Berikut.
                    </div>&nbsp;
                    <p>
                        <label>Tarikh :</label>
                        <fmt:formatDate type="time" pattern="dd/MM/yyyy" value="<%=new java.util.Date()%>"/>
                        &nbsp;</p>
                    <p>
                        <label>Daerah :</label>
                        ${kodDaerah} - ${daerah} &nbsp;
                    </p>
                    <p>
                        <label><em>*</em>Cara Aduan :</label>
                        <s:select name="caraMohon"  style="width:139px;" id="cara" value="${actionBean.caraMohon}">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${actionBean.senaraiKodCaraPermohonan}" label="nama" value="kod" sort="nama" />
                        </s:select>&nbsp;
                    </p>
                    <p>
                        <label><em>*</em>Ringkasan Aduan :</label>
                        <s:textarea name="sebab" id="sebab" value="${actionBean.sebab}"  rows="5" cols="50" onkeydown="limitText(this,2500);" onkeyup="limitText(this,2500);"/>
                    </p>
                    <p>
                        <label><em>*</em>Peruntukan Seksyen :</label>
                        ${actionBean.permohonan.kodUrusan.kod} - ${actionBean.permohonan.kodUrusan.nama}
                    </p>

                </fieldset >
                <br>
                <fieldset class="aras1">
                    <legend>Maklumat Pengadu</legend>
                    <p>
                        <label><em>*</em>Nama :</label>
                        <s:text name="penyerahNama" id="penyerahNama" size="42" maxlength="250" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label>Jenis Pengenalan :</label>
                        <s:select name="jenisPengenalanPenyerah"  value="${actionBean.jenisPengenalanPenyerah}"  style="width:139px;" id="pengenalan" onchange="jenisPengenalan()">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod" sort="nama" />
                        </s:select>
                        &nbsp;
                    </p>
                    <p id="noPengenalanLain">
                        <label>No.Pengenalan :</label>
                        <s:text name="penyerahNoPengenalanLain" id="penyerahNoPengenalanLain" maxlength="20" onkeyup="this.value=this.value.toUpperCase();" />
                        &nbsp;
                    </p>
                    <p id="noPengenalanBaru" style="visibility: hidden; display: none">
                        <label>No.Pengenalan :</label>
                        <s:text name="penyerahNoPengenalanBaru" id="penyerahNoPengenalanBaru" maxlength="12" onkeyup="validateNumber(this,this.value);" onchange="validateKPLength(this.value);"/>
                        <font color="red" size="1">cth : 850510075342 </font>
                        &nbsp;
                    </p>
                    <p>
                        <label>Alamat :</label>
                        <s:text name="penyerahAlamat1"  size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        <s:text name="penyerahAlamat2"  size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>

                    </p>
                    <p>
                        <label> &nbsp;</label>
                        <s:text name="penyerahAlamat3"  size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>

                    </p>
                    <p>
                        <label> &nbsp;</label>
                        <s:text name="penyerahAlamat4"  size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>

                        &nbsp;
                    </p>
                    <p>
                        <label>Poskod :</label>
                        <s:text name="penyerahPoskod" id="poskod" size="19" maxlength="5" onkeyup="validateNumber(this,this.value);" onchange="validatePoskodLength(this.value);"/>

                    </p>
                    <p>
                        <label>Negeri :</label>
                        <s:select name="kodNegeriPenyerah"  style="width:139px;" value="${actionBean.kodNegeriPenyerah}">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" sort="nama" />
                        </s:select>
                    </p>
                    <p>
                        <label>No.Telefon :</label>
                        <s:text name="penyerahNoTelefon1" id="telefon" size="19" maxlength="12" onkeyup="validateNumber(this,this.value);" onchange="validateTelLength(this.value);"/>

                    </p>
                    <p>
                        <label>Email :</label>
                        <s:text name="penyerahEmail" id="email" size="40" maxlength="100" onblur="return ValidateEmail()" class="normal_text"/>

                    </p>
                </fieldset>
                <br>

                <div id="othersSeksyen">
                    <fieldset class="aras1">
                        <legend>Lokasi Kejadian</legend>
                        <div class="instr-fieldset">
                            Sila masukkan maklumat lokasi.
                        </div>
                        <div id="lokasiKejadianDiv">
                            <p id="lokasikejadian1">
                                <label><em>*</em>Bandar/Pekan/Mukim :</label>
                                <s:select name="kodBpm" id="bpm" value="${actionBean.kodBpm}">
                                    <s:option value=""> Sila Pilih </s:option>
                                    <c:forEach items="${actionBean.listBandarPekanMukim}" var="line">
                                        <s:option value="${line.kod}">${line.bandarPekanMukim} - ${line.nama}</s:option>
                                    </c:forEach>
                                </s:select>
                                &nbsp;
                            </p>
                            <p id="lokasikejadian2">
                                <label>Jenis Tanah :</label>
                                <s:select name="jenisMilik" id="milik" value="${actionBean.jenisMilik}">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodPemilikan}" label="nama" value="kod" sort="nama" />
                                </s:select>
                                &nbsp;
                            </p>
                            <p id="lokasikejadian3">
                                <label>Jenis Nombor:</label>
                                <s:select name="kodLotTanah" id="kodLot"  value="${actionBean.kodLotTanah}">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodLot}" label="nama" value="kod" sort="nama" />
                                </s:select>
                                <s:text name="noLot" onkeyup="validateNumber(this,this.value);"/> &nbsp;
                            </p>
                        </div>
                        <p>
                            <label><em>*</em> Lokasi :</label>
                            <s:textarea name="lokasi" id="message" rows="5" cols="50" onkeydown="limitText(this,499);" onkeyup="limitText(this,499);" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
                        </p>
                    </fieldset>
                </div>


                <p align="center">
                    <s:button class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div');" name="saveMohon" value="Simpan"/>
                </p>
            </div>




        </fieldset>
    </div>
</s:form>