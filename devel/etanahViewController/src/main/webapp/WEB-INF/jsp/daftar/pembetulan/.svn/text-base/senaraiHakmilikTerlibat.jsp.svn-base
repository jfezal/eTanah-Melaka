<%-- 
    Document   : senaraiHakmilikTerlibat
    Created on : Jan 27, 2010, 11:58:07 AM
    Author     : mohd.fairul
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/popup.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-impromptu.js"></script>
<%--<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.3.2.min.js"></script>--%>
<script type="text/javascript">

    $(document).ready( function() {
        $('#MAH').hide();
        $('#MHA').hide();
        $('#MHS').hide();
        $('#PP').hide();
        

    } );
    function pickPut(){
        var radioVal;
        var codeTrans;

        var txt = '<p class=\"jqismooth\">Sila Pilih Urusan Pembetulan</p>'+
            '<input type="radio" name="urusanPembetulan" id="mah" value="mah" class="radioinput" checked/>Maklumat Asas Hakmilik<br\>'+
            '<input type="radio" name="urusanPembetulan" id="mha" value="mha" class="radioinput" />Maklumat Hakmilik Asal<br\>'+
            '<input type="radio" name="urusanPembetulan" id="mhs" value="mhs" class="radioinput" />Maklumat Hakmilik Sebelumkini<br\>';

                   $.prompt(txt,{
                       buttons: { Batal: 0, Ok: 1 },
                        focus: 1,
                        submit:function(v,m,f){
                            var str;
                            
                            if(v==1){
                                $.each(f,function(i,obj){
                                str = obj;
                                //alert(str);
                            });
                                pilihPembetulan(str);}
                            else if(v==0)
                                {
                                pilihPembetulan(null);
                                $.prompt.close()
                                }
                            else return true;


                        }
                    });
}



function pilihPembetulan(rV)
{
var rv = rV;
if(rv == "mah"){
        
    $('#MAH').show();
    $('#MHA').hide();
    $('#MHS').hide();
}
else if(rv == "mha"){
    $('#MHA').show();
    $('#MAH').hide();
    $('#MHS').hide();
}
else if (rv == "mhs")
{
    $('#MHS').show();
    $('#MAH').hide();
    $('#MHA').hide();
}
else
    {
    $('#MHS').hide();
    $('#MAH').hide();
    $('#MHA').hide();
    }
}

function enableDisable(oChk){
var disable = !oChk.checked;
var arglen = arguments.length;
var obj, startIndex = 1;
var frm = oChk.form;
for (var i=startIndex;i<arglen;i++){
    obj = frm.elements[arguments[i]];
    if (typeof obj=="object"){
        if (document.layers) {
            if (disable){
                obj.onfocus=new Function("this.blur()");
                if (obj.type=="text") obj.onchange=new Function("this.value=this.defaultValue");
            }
            else {
                obj.onfocus=new Function("return");
                if (obj.type=="text") obj.onchange=new Function("return");
            }
        }
        else obj.disabled=disable;
    }
}
}
function pasti(idHakmilik)
{$.prompt('Adakah anda pasti '+idHakmilik,{ buttons: { Ya: true, Tidak: false } });}

function pp()
{
    $('#PP').show();
}

</script>

<s:form beanclass="etanah.view.stripes.nota.pembetulanActionBean">
    <div class="subtitle displaytag">
        <fieldset class="aras1">
            <legend>
                Senarai Hakmilik Terlibat
            </legend>
            <p style="color:red">
                *Sila pilih Hakmilik untuk membuat pembetulan.<br/>
                *Klik pada Id Hakmilik untuk melihat maklumat hakmilik terperinci.
            </p>
            <p align="center"><label></label>
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}"  cellpadding="0" cellspacing="0"
                               requestURI="/common/maklumat_hakmilik_permohonan" id="line">
                    <display:column title="Bil">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}"/>
                    <display:column title="Baiki"><div align="center">
                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="pp()"/>
                </div></display:column>
                </display:table>


                &nbsp;
            </p>

        </fieldset>

    </div>
    <div id="PP">
        <fieldset class="aras1">
            <legend>
                Pilihan Pembetulan
            </legend>
            <p style="color:red">
                *Sila pilih Hakmilik untuk membuat pembetulan.<br/>
                *Klik pada Id Hakmilik untuk melihat maklumat hakmilik terperinci.
            </p>
            <p align="center">
            <table class="tablecloth">
                <tr><th>Bil</th><th>Perkara Pembetulan</th><th>Pilih</th></tr>
                <tr><td>1</td><td >Maklumat Asas Hakmilik<br></td><td><s:radio name="perkaraPembetulan" value="mah" onclick="pilihPembetulan(this.value)"/></td></tr>
                <tr><td>2</td><td>Maklumat Hakmilik Asal<br></td><td><s:radio name="perkaraPembetulan" value="mha" onclick="pilihPembetulan(this.value)"/></td></tr>
                <tr><td>3</td><td>Maklumat Hakmilik Sebelumkini<br></td><td><s:radio name="perkaraPembetulan" value="mhs" onclick="pilihPembetulan(this.value)"/></td></tr>
            </table>
            </p>
            <br/>
        </fieldset>

    </div>
    <div id="MAH">
        <fieldset class="aras1">
            <legend>
                Maklumat Asas Hakmilik
            </legend>
            <p style="color:red">
                *Sila pilih Hakmilik untuk membuat pembetulan.<br/>
                *Klik pada Id Hakmilik untuk melihat maklumat hakmilik terperinci.
            </p>
            <p align="center">
            <div class="content" align="center" id="tanahMilik">

                <table class="tablecloth">
                    <tr><th>Perkara</th><th>Maklumat Lama</th><th>Maklumat Baru</th><th>Pilih</th></tr>
                    <tr><td class="s">Amaun Cukai (RM) :</td><td>${hakmilik.idHakmilik}</td><td><s:text name="amaunCukai" disabled="true"/></td><td><s:checkbox name="permohonan.sebab" onclick="javascript:enableDisable(this,'amaunCukai')"/></td></tr>
                    <tr><td class="s">Kod/Nombor Lot :</td><td>From DB</td><td><s:text name="kodNoLot" disabled="true"/></td><td><s:checkbox name="permohonan.sebab" onclick="javascript:enableDisable(this,'kodNoLot')"/></td></tr>
                    <tr><td class="s">Unit/Keluasan :</td><td>From DB</td><td><s:text name="unitKeluasan" disabled="true"/></td><td><s:checkbox name="permohonan.sebab" onclick="javascript:enableDisable(this,'unitKeluasan')"/></td></tr>
                    <tr><td class="s">Kategori Tanah :</td><td>From DB</td><td><s:text name="katTanah" disabled="true"/></td><td><s:checkbox name="permohonan.sebab" onclick="javascript:enableDisable(this,'katTanah')"/></td></tr>
                    <tr><td class="s">No. Lembaran Piawai :</td><td>From DB</td><td><s:text name="noLembaranP" disabled="true"/></td><td><s:checkbox name="permohonan.sebab" onclick="javascript:enableDisable(this,'noLembaranP')"/></td></tr>
                    <tr><td class="s">Nombor Pelan :</td><td>From DB</td><td><s:text name="noPelan" disabled="true"/></td><td><s:checkbox name="permohonan.sebab" onclick="javascript:enableDisable(this,'noPelan')"/></td></tr>
                    <tr><td class="s">Nombor Fail :</td><td>From DB</td><td><s:text name="noFail" disabled="true"/></td><td><s:checkbox name="permohonan.sebab" onclick="javascript:enableDisable(this,'noFail')"/></td></tr>
                    <tr><td class="s">Syarat Nyata :</td><td>From DB</td><td><s:text name="syaratNyata" disabled="true"/></td><td><s:checkbox name="permohonan.sebab" onclick="javascript:enableDisable(this,'syaratNyata')"/></td></tr>
                    <tr><td class="s">Sekatan Kepentingan :</td><td>From DB</td><td><s:text name="sekatanK" disabled="true"/></td><td><s:checkbox name="permohonan.sebab" onclick="javascript:enableDisable(this,'sekatanK')"/></td></tr>
                    <tr><td class="s">Taraf Permilikan :</td><td>From DB</td><td><s:text name="tarafP" disabled="true"/></td><td><s:checkbox name="permohonan.sebab" onclick="javascript:enableDisable(this,'tarafP')"/></td></tr>
                    <tr><td class="s">Tarikh Daftar :</td><td>From DB</td><td><s:text name="trhDaftar" disabled="true"/></td><td><s:checkbox name="permohonan.sebab" onclick="javascript:enableDisable(this,'trhDaftar')"/></td></tr>
                    <tr><td class="s">Jenis Rezab :</td><td>From DB</td><td><s:text name="jenisRezab" disabled="true"/></td><td><s:checkbox name="permohonan.sebab" onclick="javascript:enableDisable(this,'jenisRezab')"/></td></tr>
                    <tr><td class="s">No. Permohonan Ukur :</td><td>From DB</td><td><s:text name="noPermohonanUkur" disabled="true"/></td><td><s:checkbox name="permohonan.sebab" onclick="javascript:enableDisable(this,'noPermohonanUkur')"/></td></tr>
                    <tr><td class="s">Seksyen :</td><td>From DB</td><td><s:text name="seksyen" disabled="true"/></td><td><s:checkbox name="permohonan.sebab" onclick="javascript:enableDisable(this,'seksyen')"/></td></tr>
                    <tr><td class="s">Lokasi :</td><td>From DB</td><td><s:text name="lokasi" disabled="true"/></td><td><s:checkbox name="permohonan.sebab" onclick="javascript:enableDisable(this,'lokasi')"/></td></tr>

                </table>
            </div>

            <table style="margin-left: auto; margin-right: auto;">
                <tr>
                    <td>&nbsp;</td>
                    <td><div >

                            <s:button class="btn" onclick="doSubmit(this.form, this.name, 'page_div');" name="save" value="Simpan"/>


                        </div>
                    </td>
                </tr>
            </table>
            </p>
            <br/>
        </fieldset>
    </div>
    <div id="MHA">
        <fieldset class="aras1">
            <legend>
                Maklumat Hakmilik Asal
            </legend>
            <p style="color:red">
                *Sila Isi Maklumat Pembetulan Yang Berkenaan Sahaja.
            </p>
            <p align="center">
                <%-- <table class="tablecloth">
                     <tr><th>Perkara</th><th>Nilai Lama</th><th>Nilai Baru</th></tr>
                     <tr><td>ID Hakmilik Asal</td><td >070203SLG16152/7113<br></td><td><s:text name="hakmilikBaru"/></td></tr>
                     <tr><td>Tarikh Daftar</td><td>08/09/1905<br></td><td><s:text name="trhBaru"/></td></tr>

                </table>--%>
                <label></label>
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}"  cellpadding="0" cellspacing="0"
                               requestURI="/common/maklumat_hakmilik_permohonan" id="line">
                    <display:column title="Bil">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik Asal" />
                     <display:column property="hakmilik.tarikhDaftar" title="Tarikh Daftar" />
                        <display:column title="Baiki"><div align="center">
                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('this.form','${line.hakmilik.idHakmilik}','${line.id}')"/>
                </div></display:column>
                <display:column title="Padam">
                        <div align="center">
                            <img alt='Klik Untuk Padam' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 id='rem_${line_rowNum}' onclick="pasti('${line.id}')" onmouseover="this.style.cursor='pointer';">
                        </div>
                    </display:column>
            </display:table>


            &nbsp;
            </p>
            <table style="margin-left: auto; margin-right: auto;">
                <tr>
                    <td>&nbsp;</td>
                    <td><div >

                            <s:button class="btn" onclick="doSubmit(this.form, this.name, 'page_div');" name="savePengambilan" value="Tambah"/>


                        </div>
                    </td>
                </tr>
            </table>
            <br/>
        </fieldset>

    </div>
    <br>
 <div id="MHS">
        <fieldset class="aras1">
            <legend>
                Maklumat Hakmilik Asal
            </legend>
            <p style="color:red">
                *Sila Isi Maklumat Pembetulan Yang Berkenaan Sahaja.
            </p>
            <p align="center">
                <%-- <table class="tablecloth">
                     <tr><th>Perkara</th><th>Nilai Lama</th><th>Nilai Baru</th></tr>
                     <tr><td>ID Hakmilik Asal</td><td >070203SLG16152/7113<br></td><td><s:text name="hakmilikBaru"/></td></tr>
                     <tr><td>Tarikh Daftar</td><td>08/09/1905<br></td><td><s:text name="trhBaru"/></td></tr>

                </table>--%>
                <label></label>
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}"  cellpadding="0" cellspacing="0"
                               requestURI="/common/maklumat_hakmilik_permohonan" id="line">
                    <display:column title="Bil">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik Asal" />
                    <display:column title="Baiki"><div align="center">
                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('this.form','${line.hakmilik.idHakmilik}','${line.id}')"/>
                </div></display:column>
                <display:column title="Padam">
                        <div align="center">
                            <img alt='Klik Untuk Padam' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 id='rem_${line_rowNum}' onclick="pasti('${line.id}')" onmouseover="this.style.cursor='pointer';">
                        </div>
                    </display:column>
            </display:table>


            &nbsp;
            </p>
            <table style="margin-left: auto; margin-right: auto;">
                <tr>
                    <td>&nbsp;</td>
                    <td><div >

                            <s:button class="btn" onclick="doSubmit(this.form, this.name, 'page_div');" name="savePengambilan" value="Tambah"/>


                        </div>
                    </td>
                </tr>
            </table>
            <br/>
        </fieldset>

    </div>



</s:form>
