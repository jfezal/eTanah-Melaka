    <%--
    Document   : popup_idKumpulan
    Created on : Aug 30, 2010, 4:03:46 PM
    Author     : abdul.hakim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<%--<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>--%>

<script type="text/javascript">
    $(document).ready(function() {
        $('#tambah').hide();
        $('#carian_hakmilik').hide();
        $('#back').hide();
        $('#save').hide();
        $('#pembayar').hide();
        $('#idTag').val('${actionBean.id}');

        <c:forEach var="i" begin="1" end="${actionBean.bilHakmilik}" >
            $("#popup${i - 1}").click( function(){
                frm = this.form;
                window.open(frm.action + "/popup?hakmilik.idHakmilik="+$("#hakmilik${i - 1}").val(), "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1024,height=300");
            });
            $("#hakmilik${i - 1}").blur(function(){
                validateHakmilik(${i - 1});

            });
            $("#akaun${i - 1}").blur(function(){
                validateAkaun(${i - 1});

            });
        </c:forEach>
    });

    function hapusHakmilik(id, kump){
        if(confirm("Adakah anda pasti untuk hapuskan ID Hakmilik "+id+" dari Kumpulan "+kump+" ?")){
            var url = '${pageContext.request.contextPath}/hasil/carian_idKumpulan?deletePopup&idHakmilik='+id;
            $.get(url,
            function(data){
                $('#popUp').html(data);
                alert("Maklumat Berjaya Dikemaskini");
                <%--var m = document.getElementById('dummy');--%>
                <%--self.opener.refreshCarian1(m.value);--%>
            },'html');
        }
    }
    
    function refreshCarian1(cari){
    alert(cari);
        var url = '${pageContext.request.contextPath}/hasil/carian_idKumpulan?reloadPage&idCarian='+cari;
        $.get(url,
        function(data){
            $('#popUp').html(data);
        }, 'html');
    }

    function checkNumber(elmnt,inputTxt){
        var a = document.getElementById('bilHakmilik')

        if (isNaN(a.value)){
            alert("Nombor tidak sah.Sila masukkan Semula");
            $("#bilHakmilik").focus();
            elmnt.value = RemoveNonNumeric(inputTxt);
            return;
        }
        if(((a.value)=="")||((a.value)==0)){
            alert("Sila masukkan Bilangan Hakmilik");
            $("#bilHakmilik").val("6")
            return;
        }
    }

    function RemoveNonNumeric( strString )
    {
          var strValidCharacters = "123456789.";
          var strReturn = "6";
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

    function validateHakmilik(idxHakmilik){
        var val = $("#hakmilik" + idxHakmilik).val();
        var type = 'hakmilik';
        frm = this.form;
        if (val == null || val == "") {
            return;
        }
        $.get("${pageContext.request.contextPath}/hasil/check_idhakmilik_accountNo?doCheckHakmilik1&idHakmilik=" + val+"&type="+type,
        function(data){
            if(data =='0'){
                $("#hakmilik" + idxHakmilik).val("");
                alert("ID Hakmilik " + val + " tidak wujud!");
            }
            else{
                $("#msg" + idxHakmilik).html("OK");
                $("#akaun" + idxHakmilik).val(data);
            }
            checkingDaerah(val, idxHakmilik,type);
        });
    }

     function validateAkaun(account){
        var val = $("#akaun" + account).val();
        var type = 'akaun';
        frm = this.form;
        if (val == null || val == "") {
            return;
        }
        $.get("${pageContext.request.contextPath}/hasil/check_idhakmilik_accountNo?doCheckAkaun&account=" + val,
        function(data){
            if(data =='0'){
                $("#hakmilik" + account).val("");
                $("#akaun" + account).val("");
                $("#baki" + account).val("0");
                alert("Nombor Akaun " + val + " tidak wujud!");
            }
            else{
                $("#hakmilik" + account).val(data);
            }
            checkingDaerah(data, account,type);
        });
    }

    function checkingDaerah(idHakmilik, row,type){
        var pgunaDaerah = $("#cawPguna").val();
        var daerah = $("#daerah").val();
        $.get("${pageContext.request.contextPath}/hasil/check_idhakmilik_accountNo?checkingDaerah&idHakmilik=" + idHakmilik,
        function(data){
            if(data != pgunaDaerah){
                if(type == 'hakmilik'){
                    alert("Id Hakmilik yang dimasukkan diluar daerah "+daerah+". Sila masukkan semula.");
                }else{
                    alert("Nombor Akaun yang dimasukkan diluar daerah "+daerah+". Sila masukkan semula.");
                }
                $("#hakmilik" + row).val("");
                $("#akaun" + row).val("");
            }
        });
        
        $.get("${pageContext.request.contextPath}/hasil/carian_idKumpulan?doCheckKumpulan&idHakmilik=" + idHakmilik,
        function(data){
            if(data !='0'){
                alert("ID Hakmilik " + idHakmilik + " telah wujud dalam kumpulan "+data);
                $("#hakmilik" + row).val("");
                $("#akaun" + row).val("");
            }
        });
    }

    function closePopup(){
        <%--self.opener.refreshCarian($("#dummy").val());--%>
        self.close();
    }

    function show(){
        $('#tambah').show();
        $('#carian_hakmilik').show();
        $('#back').show();
        $('#atas').hide();
        $('#add').hide();
        $('#save').show();
        $('#ttp').hide();
    }

    function kembali(){
        $('#tambah').hide();
        $('#carian_hakmilik').hide();
        $('#back').hide();
        $('#atas').show();
        $('#add').show();
        $('#save').hide();
        $('#ttp').show();
    }

    function pbayar(){
        var c = document.getElementById('selectPembayar');
        if(c.checked){
            $('#pembayar').show();
        }
        if(!c.checked){
            $('#pembayar').hide();
        }
    }

    function populatePenyerah(btn){
        var url;
        if (btn.id == "carianPenyerah"){
            $('#kod').val('1');
            var idx = $("#idPenyerah").val();
            var jenis = $('#penyerahKod').val();
            if (idx == null || idx.length == 0){
                //alert("Sila masukkan ID Penyerah.");
                window.open("${pageContext.request.contextPath}/common/req_penyerah_info?showForm&popup=true&jenisPenyerah=" + jenis, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=1024");

                return;
            }
            if (jenis == '0'){
                alert('Sila pilih Jenis Penyerah');
                return;
            } else if (jenis == '01'){ // PEGUAM
                url = "${pageContext.request.contextPath}/common/req_peguam_info?idPeguam=" + idx;
            } else if (jenis == '02'){ // JUBL
                url = "${pageContext.request.contextPath}/common/req_jubl_info?idJUBL=" + idx;
            } else if (jenis == '00') {
                url = "${pageContext.request.contextPath}/common/req_unit_info?idUnit=" + idx;
            } else if (jenis == '05') {
                url = "${pageContext.request.contextPath}/common/req_agensi_info?idAgensi=" + idx;
            } else if (jenis == '06') { //Jabatan Kerajaan
                url = "${pageContext.request.contextPath}/common/req_agensi_info?idAgensi=" + idx;
            } else if (jenis == '07') { //Badan Berkanun
                url = "${pageContext.request.contextPath}/common/req_agensi_info?idAgensi=" + idx;
            }
        } else if (btn.id == "carianPihak"){
            $('#kod').val('2');
            var jP = $("#penyerahJenisPengenalan").val();
            var noP = $("#penyerahNoPengenalan").val();
            if (jP == null || jP.length == 0 || noP == null || noP.length == 0){
                alert("Sila masukkan Jenis Pengenalan/No.Pengenalan.");
                return;
            }
            url = "${pageContext.request.contextPath}/common/req_pihak_info?jenisPengenalan=" + jP
                + "&noPengenalan=" + noP;
        }

        $.get(url,
        function(data){
            if (data == null || data.length == 0){
                alert("Maklumat tidak dijumpai");
                return;
            }
            var p = data.split(DELIM);
            $('#penyerahJenisPengenalan').val(p[0]);
            $('#penyerahNoPengenalan').val(p[1]);
            $("#penyerahNama").val(p[2]);
            $("#penyerahAlamat1").val(p[3]);
            $("#penyerahAlamat2").val(p[4]);
            $("#penyerahAlamat3").val(p[5]);
            $("#penyerahAlamat4").val(p[6]);
            $("#penyerahPoskod").val(p[7]);
            $("#penyerahNegeri").val(p[8]);
            $("#penyerahNoTelefon").val(p[9]);
            $("#penyerahEmail").val(p[10]);
        });
    }
</script>
<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:30px;
        margin-right:0.5em;
        text-align:right;
        width:25em;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:30em;
    }
</style>
<div id="popUp">
    <s:messages/>
    <s:form beanclass="etanah.view.stripes.hasil.SearchingIDKumpulanActionBean" id="carian_idKump">
        <s:text name="pgunaCaw.kod" style="display:none;" id="cawPguna"/>
        <div id="atas">
        <fieldset class="aras1">
            <legend>Maklumat Kumpulan</legend>
            <p>
                <label>ID Kumpulan :</label>
                ${actionBean.id}&nbsp;
                <s:text name="dummyId" style="display:none;" id="dummy"/>
            </p>
            <p>
                <label>Nama Kumpulan :</label>
                ${actionBean.kumpulanAkaun.catatan}&nbsp;
            </p>
            <p>
                <label>Daerah :</label>
                ${actionBean.caw}&nbsp;<s:text name="caw" value="${actionBean.caw}" id="daerah" style="display:none;"/>
            </p>

            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiAkaun}" id="line">
                    <display:column title="No"><div align="center">${line_rowNum}.</div></display:column>
                    <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                        <display:column property="noAkaun" title="Nombor Akaun"/>
                    </c:if>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik"/>
                    <display:column property="pemegang.nama" title="Pembayar"/>
                    <display:column property="hakmilik.noLot" title="No. Lot/PT"/>
                    <%--<display:column property="hakmilik.kodHakmilik.kod" title="Jenis Hakmilik"/>--%>
                    <display:column title="Nama Pemilik Tanah (No Pengenalan)" >
                        <ol>                               
                            <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai" varStatus="a">
                                <%--c:if test="${(senarai.jenis.kod eq 'PM' or senarai.jenis.kod eq 'PA' or senarai.jenis.kod eq 'WAR' or
                                          senarai.jenis.kod eq 'ASL' or senarai.jenis.kod eq 'JA' or senarai.jenis.kod eq 'JK' or
                                          senarai.jenis.kod eq 'KL' or senarai.jenis.kod eq 'PDP' or senarai.jenis.kod eq 'PK' or
                                          senarai.jenis.kod eq 'PLK' or senarai.jenis.kod eq 'PP' or senarai.jenis.kod eq 'RP' or
                                          senarai.jenis.kod eq 'WKL' or senarai.jenis.kod eq 'WPA' or senarai.jenis.kod eq 'WS')
                                          and senarai.aktif eq 'Y'}"--%>
                                <c:if test="${(senarai.jenis.kod eq 'PM') and senarai.aktif eq 'Y'}">
                                    <li>
                                        <c:out value="${senarai.pihak.nama}" />  
                                        <c:if test="${senarai.pihak.noPengenalan ne null}">
                                            (<c:out value="${senarai.pihak.noPengenalan}" />)
                                        </c:if>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </ol>
                    </display:column>
                    <display:column property="hakmilik.kodStatusHakmilik.nama" title="Status Hakmilik"/>
                    <display:column title="Hapus ID Hakmilik" style="width:90px">
                        <c:choose>
                                <c:when test="${actionBean.pgunaCaw.kod eq actionBean.kumpulanAkaun.cawangan.kod}">
                                    <div align="center"><a href="#">
                                         <img alt='Klik Untuk Hapus' src='${pageContext.request.contextPath}/images/not_ok.gif'
                                              onclick="hapusHakmilik('${line.hakmilik.idHakmilik}', '${actionBean.kumpulanAkaun.idKumpulan}');" /></a>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div align="center">
                                        <img alt='Tidak Dibenarkan Hapus' src='${pageContext.request.contextPath}/images/lock.gif' />
                                    </div>
                                </c:otherwise>
                            </c:choose>
                    </display:column>
                </display:table>
            </div>
            <%--<p>
                <label>&nbsp;</label>
                <s:checkbox name="" id="selectPembayar" onclick="pbayar();"/> Seragamkan Pembayar.
            </p>--%>
            <br>
        </fieldset><%--<a href="#" onclick="show();" style="text-decoration:underline">Tambah</a>--%>
        <p></p>
        <fieldset class="aras1" id="pembayar">
            <legend>Maklumat Pembayar</legend>
            <p>
                <label>Carian Pembayar </label>
                <s:select name="" id="penyerahKod" onchange="">
                    <s:option value="0">Individu/Syarikat</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodPenyerah}" label="nama" value="kod"/>
                </s:select>
                    <s:text name=" " size="5" id="idPenyerah" maxlength="4"/>
                <input type="button" id="carianPenyerah"
                       value="Cari" class="btn" onclick="javascript:populatePenyerah(this);" />
                (Biarkan kosong dan klik "Cari" untuk membuat rujukan)
            </p>

            <p>
                <label for="Jenis Pengenalan">Carian: No. Pengenalan :</label>
                <s:select name="" id="penyerahJenisPengenalan" onchange="clearNoPengenalan();">
                    <s:option value="0">Pilih Jenis...</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                </s:select>
                <s:text name=" " id="penyerahNoPengenalan" onkeyup="dodacheck(this.value);"
                        onblur="doUpperCase(this.id)"/>
                <input type=button name="searchPenyerah" value="Cari" class="btn" id="carianPihak"
                       onclick="javascript:populatePenyerah(this);" />
            </p>

            <p>
                <label>Nama</label><s:text name=" " id="penyerahNama" size="42" onblur="doUpperCase(this.id)"/><em>*</em>
            </p>

            <p>
                <label>Alamat</label>
                <s:text name=" " id="penyerahAlamat1" size="30" onblur="doUpperCase(this.id)"/><em>*</em>
            </p>

            <p>
                <label>&nbsp;</label>
                <s:text name=" " id="penyerahAlamat2" size="30" onblur="doUpperCase(this.id)"/>
            </p>

            <p>
                <label>&nbsp;</label>
                <s:text name=" " id="penyerahAlamat3" size="30" onblur="doUpperCase(this.id)"/>
            </p>

            <p>
                <label>Bandar</label>
                <s:text name=" " id="penyerahAlamat4" size="30" onblur="doUpperCase(this.id)"/>
            </p>

            <p>
                <label>Poskod</label>
                <s:text name=" " maxlength="5" id="penyerahPoskod" size="17" onkeyup="validateNumber(this,this.value);" />
            </p>

            <p>
                <label>Negeri</label>
                <s:select name=" .kod" id="penyerahNegeri" >
                    <s:option value="0">Pilih ...</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                </s:select><em>*</em> <s:submit  name="updatePenyerah" value="Kemaskini Penyerah" class="longbtn"/>
            </p>
            <p>
                <label>No.Telefon</label>
                <s:text name=" " id="penyerahNoTelefon" size="15"/>
            </p>
            <p>
                <label>Email</label>
                <s:text name=" " id="penyerahEmail" size="50"/>
            </p>
            <p align="center">
                <s:submit name=" " value="Simpan" class="btn" />
                <s:button name=" " value="Isi Semula" class="btn" onclick="clearText('carian_idKump');"/>
            </p>
        </fieldset>
        </div>
        <div class="subtitle" id="tambah">
            <fieldset class="aras1">
                <legend>ID Hakmilik Terlibat</legend>
                <p class=instr>
                    <em><font color="black">Masukkan ID Hakmilik bagi Hakmilik-Hakmilik yang terlibat.</font></em>
                </p>

                <p>
                    <label>ID Kumpulan :</label>
                    ${actionBean.id}&nbsp;
                    <s:text name="dummyId" style="display:none;" id="dummy"/>
                </p>
                <p>
                    <label>Nama Kumpulan :</label>
                    ${actionBean.kumpulanAkaun.catatan}&nbsp;
                </p>
                <p>
                    <label>Daerah :</label>
                    ${actionBean.caw}&nbsp;
                </p>
                    
                <div align="center">
                    <%--<s:errors field="list"/>--%>
                    <table border=0 align="center" class="tablecloth">
                        <tr>
                            <th>Bil.</th>
                            <th class="hakmilik">ID Hakmilik</th>
                            <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                                <th class="akaun">Nombor Akaun</th>
                            </c:if>
                            <th>Bil</th>
                            <th class="hakmilik">ID Hakmilik</th>
                            <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                                <th class="akaun">Nombor Akaun</th>
                            </c:if>
                        </tr>

                        <tr>
                            <c:forEach var="i" begin="1" end="${actionBean.bilHakmilik}" >
                                <td align="center" style="text-align:center;">${i}. </td>
                                <td class="hakmilik">
                                    <s:text name="senaraiHakmilik[${i - 1}].idHakmilik" id="hakmilik${i - 1}" onkeyup="this.value=this.value.toUpperCase();"/>
                                </td>
                                <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                                    <td  class="akaun">
                                        <s:text name="senaraiAccount[${i - 1}].noAkaun" id="akaun${i - 1}" onkeyup="this.value=this.value.toUpperCase();"/>
                                    </td>
                                </c:if>
                                <c:if test="${i % 2 == 0}" >
                                </tr>
                            </c:if>
                        </c:forEach>
                    </table><br>
                </div>
            </fieldset>
        </div>
        <div class="subtitle">
                <table border="0" style="width:99.2%">
                <tr>
                    <td align="center">
                        <c:choose>
                            <c:when test="${actionBean.pgunaCaw.kod eq actionBean.selectedDaerah}">
                                <s:button name=" " value="Tambah" id="add" class="btn" onclick="show();"/>
                                <s:button name=" " value="Kembali" id="back" class="btn" onclick="kembali();"/>
                                <s:submit name="simpan" value="Simpan" id="save" class="btn"/>
                                <s:submit name="back" value="Kembali" id="ttp" class="btn" />
                            </c:when>
                            <c:otherwise>
                                <s:button name=" " value="Tambah" id="add" class="btn" onclick="show();" disabled="true"/>
                                <s:button name=" " value="Kembali" id="back" class="btn" onclick="kembali();"/>
                                <s:submit name="simpan" value="Simpan" id="save" class="btn"/>
                                <s:submit name="back" value="Kembali" id="ttp" class="btn"/>
                            </c:otherwise>
                        </c:choose>

                    </td>
                </tr>
            </table>
        </div>

    </s:form>
    <s:form action="kumpulan_akaun" id="carian_hakmilik">
        <div class="content" id="carian_hm">
            <fieldset class="aras1">
                <legend>Carian Hakmilik</legend>
                <p class=instr align="center">
                    <font color="black">KliK Butang Carian untuk membuat carian.</font><br>
                        <em>Peringatan :</em> Ditetapkan 500 hakmilik sahaja untuk setiap kumpulan.
                </p>
                <br>
                <div align="center">
                    <s:submit name="" value="Carian Hakmilik" class="btn"/>
                    <s:text name="idTagKumpulan" id='idTag' style="display:none;"/>
                    <s:text name="flagKumpulan" id='idTag' value="kumpulan" style="display:none;"/>
                </div>
            <br>
            </fieldset>
        </div>
    </s:form>
</div>