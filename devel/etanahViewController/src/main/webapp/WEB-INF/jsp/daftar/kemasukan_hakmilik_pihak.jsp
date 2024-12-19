<%--
    Document   : kemasukan_hakmilik_pihak
    Created on : 15-Oct-2009, 03:49:43
    Author     : khairil
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head><title>Carian Pihak</title>
    <link type="text/css" rel="stylesheet"
          href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
    <link type="text/css" rel="stylesheet"
          href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
    <link type="text/css" rel="stylesheet"
          href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
    <script type="text/javascript"
    src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
    <%-- <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>--%>
    <%--  <script type="text/javascript" src="${pageContext.request.contextPath}/pub/scripts/jquery.numeric.pack.js"></script>--%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/scripts/jquery.validate.js"></script>
    <script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
    <script type="text/javascript">
      $(document).ready(function() {
        maximizeWindow();  
        $(".wideselect")
                .mouseover(function() {
          $(this).data("origWidth", $(this).css("width")).css("width", "auto");
        })
                .mouseout(function() {
          $(this).css("width", $(this).data("origWidth"));
        });
        $('#Cari').click(function() {
          return validNoKp();
        });
        $("#jenisPihak").val("PM");
        $("#jenisPengenalan").change(function() {
          dodacheck($("#noPengenalan").val());
        });
        $('#noPengenalan').keyup(function() {
          dodacheck($('#noPengenalan').val());
        });
        $('#suratPoskod').keyup(function() {
          validNumber($('#suratPoskod').val(), 'suratPoskod');
        });
        $('#poskod').keyup(function() {
          validNumber($('#poskod').val(), 'poskod');
        });
        $('#cawanganPoskod').keyup(function() {
          validNumber($('#cawanganPoskod').val(), 'cawanganPoskod');
        });
        $('#suratCawanganPoskod').keyup(function() {
          validNumber($('#suratCawanganPoskod').val(), 'suratCawanganPoskod');
        });

        var ic = $('#ic').val(); // auto get ic no. for 'k/p baru'
        if (ic == 'B') {
          var noIC = $('#noIC').val();
          if (noIC != null) {
            var jantina = noIC.substring(8, 12); // get gender from ic no.
            if (jantina % 2 == 0) {
              $('#jantina').val('2');
            } else {
              $('#jantina').val('1');
            }
          }
        }

      });
      <%--function checkPemohonUtkRizab(f){
      var luasAmbil = f;
      var idHakmilik = '${actionBean.idHakmilik}';
      $.post('${pageContext.request.contextPath}/pihakBerkepentingan?checkPemohon&idHakmilik='+idHakmilik+'&luasAmbil='+luasAmbil,
      function(data){
          if(data != '0'){
              //alert('Pelan untuk no lot '+ noLot +' tiada');
              $("#luas").val(data);

                }
            }, 'html');--%>

              function validNumber(val, id) {
                var strLength = val.length;
                var lchar = val.charAt((strLength) - 1);
                if (isNaN(lchar)) {
                  var tst = val.substring(0, (strLength) - 1);
                  $('#' + id).val(tst);
                }
              }
              function dodacheck(val) {
                //var mikExp = /[1\\@\\\#%\^\&\*\(\)\[\]\+\_\{\}\`\~\1\|]/;
                var v = $('#jenisPengenalan').val();
                if (v == 'B') {
                  //var strPass = val;
                  //var strLength = strPass.length;
                  $('#noPengenalan').attr("maxlength", "12");
                  //if(strLength > 12) {
                  // var tst = val.substring(0, (strLength) - 1);
                  // $('#noPengenalan').val(tst);
                  //}
                  validNumber(val, 'noPengenalan');
                } else {
                  $('#noPengenalan').attr("maxlength", "50");
                }
              }

              function validNoKp() {
                if ($('#jenisPengenalan').val() == '') {
                  alert('Sila Pilih Jenis Pengenalan');
                  return false;
                }
                else if ($('#jenisPengenalan').val() == '0') {
                  $('#kp').val('-');
                  $('#kp').hide();
                }
                else if ($('#jenisPengenalan').val() == 'X') {
                   $('#kp').val('-');      
                   $('#kp').hide();
                }
                else if ($('#jenisPengenalan').val() == 'B') {
                  if (isNaN($('#noPengenalan').val()) || $('#noPengenalan').val().length != 12 || $('#noPengenalan').val() == '') {
                    alert('Format No Pengenalan Baru Tidak Sah');
                    return false;
                  }
                }
                else{
                     if ($('#noPengenalan').val() == '') {
                        alert('Sila Masukkan No Pengenalan');
                        return false;
                     }    
                }
                    
              }
              function copyAdd() {
                if ($('input[name=add1]').is(':checked')) {
                  $('#suratAlamat1').val($('#alamat1').val());
                  $('#suratAlamat2').val($('#alamat2').val());
                  $('#suratAlamat3').val($('#alamat3').val());
                  $('#suratAlamat4').val($('#alamat4').val());
                  $('#suratPoskod').val($('#poskod').val());
                  $('#suratNegeri').val($('#negeri').val());
                } else {
                  $('#suratAlamat1').val('');
                  $('#suratAlamat2').val('');
                  $('#suratAlamat3').val('');
                  $('#suratAlamat4').val('');
                  $('#suratPoskod').val('');
                  $('#suratNegeri').val('');
                }
              }

              function copyAddCaw() {
                if ($('input[name=add2]').is(':checked')) {
                  $('#suratCawanganAlamat1').val($('#cawanganAlamat1').val());
                  $('#suratCawanganAlamat2').val($('#cawanganAlamat2').val());
                  $('#suratCawanganAlamat3').val($('#cawanganAlamat3').val());
                  $('#suratCawanganAlamat4').val($('#cawanganAlamat4').val());
                  $('#suratCawanganPoskod').val($('#cawanganPoskod').val());
                  $('#suratCawanganNegeri').val($('#cawanganNegeri').val());
                } else {
                  $('#suratCawanganAlamat1').val('');
                  $('#suratCawanganAlamat2').val('');
                  $('#suratCawanganAlamat3').val('');
                  $('#suratCawanganAlamat4').val('');
                  $('#suratCawanganPoskod').val('');
                  $('#suratCawanganNegeri').val('');
                }
              }
              function checkPoskod(id) {
//                if ($('#' + id).val().length != 5) {
//                  alert('Poskod tidak sah');
//                  return false;
//                } else {
//                  return true;
//                }
                    return true;
              }
              function save(event, f) {
                var check1;
                //var check2;
                check1 = checkPoskod('poskod');
                //check2 = checkPoskod('suratPoskod');
                //alert(check1);
                // alert(check2);
                
               if ($('#kp').val() == '') { 
                  $('#kp').val('-');
               }
                
                if (check1 == true) {
                  var q = $(f).formSerialize();
                  var url = f.action + '?' + event;
                  $.blockUI({
                    message: $('#displayBox'),
                    css: {
                      top: ($(window).height() - 50) / 2 + 'px',
                      left: ($(window).width() - 50) / 2 + 'px',
                      width: '50px'
                    }
                  });
                  $.post(url, q,
                          function(data) {

                            if (data == '0')
                            {
                              alert('Sila masukan data pada label yang bertanda *. ');
                            } else {
                              //alert(data);
                              //$('#perincianHakmilik',opener.document).html(data);
                              //alert('Kemaskini Pihak Berjaya');
                              $('#maklumatHakmilik', opener.document).html(data);
                              var v = '${actionBean.idHakmilik}';
                              $.get("${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?idHakmilik=" + v,
                                      function(data) {
                                        //alert(data);
                                        $("#perincianHakmilik", opener.document).show();
                                        $("#perincianHakmilik", opener.document).html(data);
                                        $.unblockUI();

                                      });
                              self.close();
                            }

                          }, 'html');
                }
              }
    </script>
    <style type="text/css">
      input.error { background-color: yellow; }
    </style>
  </head>
  <body>
    <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
    <s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
    <div class="subtitle">
      <s:form beanclass="etanah.view.stripes.PihakBerkepentinganActionBean" >
        <%--<s:hidden name="pihak.nama"/>
        <s:hidden name="pihak.idPihak"/>
        <s:hidden name="pihak.jenisPengenalan.kod"/>
        <s:hidden name="pihak.jenisPengenalan.nama"/>
        <s:hidden name="pihak.noPengenalan"/>
        <s:hidden name="pihak.bangsa.kod"/>
        <s:hidden name="pihak.suku.kod"/>
        <s:hidden name="pihak.alamat1"/>
        <s:hidden name="pihak.alamat2"/>kodCawangan
        <s:hidden name="pihak.alamat3"/>
        <s:hidden name="pihak.alamat4"/>
        <s:hidden name="pihak.poskod"/>
        <s:hidden name="pihak.negeri.kod"/>
        <s:hidden name="pihak.negeri.nama"/>
        <s:hidden name="permohonanPihak.jenis.kod"/>
        <s:hidden name="pihak.suratAlamat1"/>
        <s:hidden name="pihak.suratAlamat2"/>
        <s:hidden name="pihak.suratAlamat3"/>
        <s:hidden name="pihak.suratAlamat4"/>
        <s:hidden name="pihak.suratNegeri.kod"/>
        <s:hidden name="pihak.suratNegeri.nama"/>
        <s:hidden name="pihak.suratPoskod"/>--%>
        <s:hidden name="idHakmilik" value="${actionBean.idHakmilik}" id="idHakmilik"/>
        <s:messages/>
        <s:errors/>
        <c:if test="${!actionBean.tambahCawFlag}">
          <c:if test="${actionBean.hideCari}">
            <fieldset class="aras1">
              <legend>
                Carian Pihak
              </legend>
              <p>
                <label for="Jenis Pengenalan">Jenis Pengenalan :</label>
                <s:select name="pihak.jenisPengenalan.kod" id="jenisPengenalan">
                  <s:option value="">Sila Pilih</s:option>
                  <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                </s:select>
              </p>
              <p>
                <label for="noKp">No. Pengenalan :</label>
                <s:text name="pihak.noPengenalan" id="noPengenalan" size="40" />
              </p>
              <p>
                <label>&nbsp;</label>
                <s:submit name="cariPihak" value="Cari" id="Cari" class="btn"/>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
              </p>
            </fieldset>
          </c:if>

          <c:if test="${actionBean.showF}">
            <fieldset class="aras1">
              <legend>Maklumat Pihak Berkepentingan</legend>

              <c:if test="${!actionBean.cariFlag}">
                <p>
                  <label for="nama"><font color="red">*</font>Nama :</label>
                      <s:text name="pihak.nama" id="nama" size="40" style="text-transform:uppercase"/>
                </p>

                <p>
                  <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                      <s:select name="pihak.jenisPengenalan.kod" id="jenispengenalan">
                        <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                  </s:select>
                </p>
                <p>
                  <label for="No Pengenalan"></font>No Pengenalan :</label>
                      <s:text name="pihak.noPengenalan" id="kp" size="40"/>
                </p>
                <p>
                  <label>Bangsa/Pertubuhan/Syarikat :</label>
                  <s:select class="wideselect" name="pihak.bangsa.kod" id="bangsa">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${actionBean.senaraiKodBangsa}" label="nama" value="kod" />
                  </s:select>
                </p>
                <p>
                  <label>Warganegara :</label>
                  <s:select name="pihak.wargaNegara.kod" id="warganegara">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiWarganegara}" label="nama" value="kod" />
                  </s:select>
                </p>
                <p>
                  <label>Jantina :</label>
                  <s:select name="pihak.kodJantina" id="jantina">
                    <s:option value="">Sila Pilih</s:option>
                    <s:option value="1">Lelaki</s:option>
                    <s:option value="2">Perempuan</s:option>
                    <%--  <s:options-collection collection="${list.senaraiKodJantina}" label="nama" value="kod" />--%>
                    <%--<s:option value="L">Lelaki</s:option>
                    <s:option value="P">Perempuan</s:option>--%>
                  </s:select>
                </p>

              </c:if>

              <c:if test="${actionBean.cariFlag}">
                <p>
                  <label for="nama">Nama :</label>
                  ${actionBean.pihak.nama}&nbsp;<s:hidden name="pihak.nama"/>
                </p>
                <p>
                  <label for="Jenis Pengenalan">Jenis Pengenalan :</label>
                  ${actionBean.pihak.jenisPengenalan.nama}&nbsp;
                  <s:hidden name="ic" value="${actionBean.pihak.jenisPengenalan.kod}" id="ic"/>
                </p>
                <p>
                  <label for="No Pengenalan">No Pengenalan :</label>
                  ${actionBean.pihak.noPengenalan}&nbsp;
                  <s:hidden name="noIC" value="${actionBean.pihak.noPengenalan}" id="noIC"/>
                </p>
              </c:if>

              <p> 
                <label for="nama"><font color="red">*</font>Jenis Pihak Berkepentingan :</label>
                    <s:select class="wideselect" id="jenisPihak" name="pihakBerkepentingan.jenis.kod" value="${actionBean.pihakBerkepentingan.jenis.kod}">
                      <%--<s:option value="" >Sila Pilih</s:option>--%>
                      <s:options-collection collection="${actionBean.senaraiKodPenerima}" label="nama" value="kod"/>
                    </s:select>
              </p>


              <c:if test="${actionBean.cariFlag}">
                <s:hidden name="idPihak" value="${idPihak}"/>
                <s:hidden name="pihak.nama"/>
                <s:hidden name="pihak.idPihak"/>
                <s:hidden name="pihak.jenisPengenalan.kod"/>
                <s:hidden name="pihak.jenisPengenalan.nama"/>
                <s:hidden name="pihak.noPengenalan"/>
                <s:hidden name="pihak.bangsa.kod"/>
                <s:hidden name="pihak.suku.kod"/>
                <%-- <s:hidden name="pihak.alamat1"/>
                 <s:hidden name="pihak.alamat2"/>
                 <s:hidden name="pihak.alamat3"/>
                 <s:hidden name="pihak.alamat4"/>
                 <s:hidden name="pihak.poskod"/>
                 <s:hidden name="pihak.negeri.kod"/>
                 <s:hidden name="pihak.negeri.nama"/>--%>
                <s:hidden name="pihak.wargaNegara.kod"/>
                <s:hidden name="permohonanPihak.jenis.kod"/>
                <%--  <s:hidden name="pihak.suratAlamat1"/>
                  <s:hidden name="pihak.suratAlamat2"/>
                  <s:hidden name="pihak.suratAlamat3"/>
                  <s:hidden name="pihak.suratAlamat4"/>
                  <s:hidden name="pihak.suratNegeri.kod"/>
                  <s:hidden name="pihak.suratNegeri.nama"/>kodJan
                  <s:hidden name="pihak.suratPoskod"/>--%>
                <p>
                  <label for="No Pengenalan">Warganegara :</label>
                  ${actionBean.pihak.wargaNegara.nama}&nbsp;
                </p>

                <p>
                  <label>Jantina :</label>
                  <s:select name="pihak.kodJantina" id="jantina">
                    <s:option value="">Sila Pilih</s:option>
                    <s:option value="1">Lelaki</s:option>
                    <s:option value="2">Perempuan</s:option>
                    <%--<s:options-collection collection="${list.senaraiKodJantina}" label="nama" value="kod" />--%>
                  </s:select>
                </p>
                <%--  <p>
                      <label for="alamat">Alamat Berdaftar :</label>

                                    ${actionBean.pihak.alamat1}&nbsp;
                                    <s:hidden id="alamat1" name="alamat1" value="${actionBean.pihak.alamat1}"/>
                                </p>
                                <p>
                                    <label> &nbsp;</label>
                                    ${actionBean.pihak.alamat2}&nbsp;
                                    <s:hidden id="alamat2" name="alamat2" value="${actionBean.pihak.alamat2}"/>
                                </p>

                                <p>
                                    <label> &nbsp;</label>
                                    ${actionBean.pihak.alamat3}&nbsp;
                                    <s:hidden id="alamat3" name="alamat3" value="${actionBean.pihak.alamat3}"/>
                                </p>
                                <p>
                                    <label> &nbsp;</label>
                                    ${actionBean.pihak.alamat4}&nbsp;
                                    <s:hidden id="alamat4" name="alamat4" value="${actionBean.pihak.alamat4}"/>
                                <p>
                                    <label>Poskod :</label>
                                    ${actionBean.pihak.poskod}&nbsp;
                                    <s:hidden id="poskod" name="poskod" value="${actionBean.pihak.poskod}" />
                                </p>

                                <p>
                                    <label for="Negeri">Negeri :</label>
                                    ${actionBean.pihak.negeri.nama}&nbsp;
                                    <s:hidden id="negeri" name="negeri" value="${actionBean.pihak.negeri.nama}"/>
                                </p>--%>
                <p>
                  <label for="alamat">Alamat Berdaftar :</label>
                  <s:text name="pihak.alamat1" id="alamat1" size="60" style="text-transform:uppercase"/>
                </p>


                <p>
                  <label> &nbsp;</label>
                  <s:text name="pihak.alamat2" id="alamat2" size="60" style="text-transform:uppercase"/>
                </p>


                <p>
                  <label> &nbsp;</label>
                  <s:text name="pihak.alamat3" id="alamat3" size="60" style="text-transform:uppercase"/>
                </p>
                <p>
                  <label>Bandar :</label>
                  <s:text name="pihak.alamat4" id="alamat4" size="60" style="text-transform:uppercase"/>
                <p>
                  <label>Poskod :</label>
                  <s:text name="pihak.poskod" id="poskod" size="40" maxlength="5"/>
                </p>

                <p>
                  <label for="Negeri">Negeri :</label>
                  <s:select name="pihak.negeri.kod" id="negeri">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                  </s:select>
                </p>
                <p> <label><input type="checkbox" id="add1" name="add1" value="" onclick="copyAdd();"/></label>
                  <font color="red">*Klik Untuk Salin Alamat Ke Alamat Surat Menyurat </font>
                </p>
              </c:if>
              <c:if test="${!actionBean.cariFlag}">


                <p>
                  <label for="alamat">Alamat Berdaftar :</label>
                  <s:text name="pihak.alamat1" id="alamat1" size="60" style="text-transform:uppercase"/>
                </p>


                <p>
                  <label> &nbsp;</label>
                  <s:text name="pihak.alamat2" id="alamat2" size="60" style="text-transform:uppercase"/>
                </p>


                <p>
                  <label> &nbsp;</label>
                  <s:text name="pihak.alamat3" id="alamat3" size="60" style="text-transform:uppercase"/>
                </p>
                <p>
                  <label>Bandar :</label>
                  <s:text name="pihak.alamat4" id="alamat4" size="60" style="text-transform:uppercase"/>
                <p>
                  <label>Poskod :</label>
                  <s:text name="pihak.poskod" id="poskod" size="40" maxlength="5"/>
                </p>

                <p>
                  <label for="Negeri">Negeri :</label>
                  <s:select name="pihak.negeri.kod" id="negeri">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                  </s:select>
                </p>
                <p> <label><input type="checkbox" id="add1" name="add1" value="" onclick="copyAdd();"/></label>
                  <font color="red">*Klik Untuk Salin Alamat Ke Alamat Surat Menyurat </font>
                </p>
              </c:if>
              <p>
                <label for="alamat">Alamat Surat-Menyurat:</label>
                <s:text name="pihak.suratAlamat1" id="suratAlamat1" size="60" style="text-transform:uppercase"/>
              </p>
              <p>
                <label> &nbsp;</label>
                <s:text name="pihak.suratAlamat2" id="suratAlamat2" size="60" style="text-transform:uppercase"/>
              </p>
              <p>
                <label> &nbsp;</label>
                <s:text name="pihak.suratAlamat3" id="suratAlamat3" size="60" style="text-transform:uppercase"/>
              </p>
              <p>
                <label>Bandar :</label>
                <s:text name="pihak.suratAlamat4" id="suratAlamat4" size="60" style="text-transform:uppercase"/>
              </p>
              <p>
                <label>Poskod :</label>
                <s:text name="pihak.suratPoskod" id="suratPoskod" size="40" maxlength="5"/>
              </p>

              <p>
                <label for="Negeri">Negeri :</label>
                <s:select name="pihak.suratNegeri.kod" id="suratNegeri">
                  <s:option value="">Sila Pilih</s:option>
                  <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                </s:select>
              </p>
              <c:if test="${actionBean.showAddCaw}">
                <div class="content" align="center">
                  <br>
                  Maklumat Cawangan

                  <display:table name="${actionBean.cawanganList}" id="line" class="tablecloth" >

                    <display:column title="Pilih"><s:radio name="idCawangan" value="${line.idPihakCawangan}"/></display:column>
                    <display:column property="namaCawangan" title="Nama"/>
                    <display:column title="Alamat" >${line.suratAlamat1}
                      <c:if test="${line.suratAlamat2 ne null}"> , </c:if>
                      ${line.suratAlamat2}
                      <c:if test="${line.suratAlamat3 ne null}"> , </c:if>
                      ${line.suratAlamat3}
                      <c:if test="${line.suratAlamat4 ne null}"> , </c:if>
                      ${line.suratAlamat4}</display:column>
                    <display:column property="suratPoskod" title="Poskod" />
                    <display:column property="suratNegeri.nama" title="Negeri" />

                  </display:table>
                </div>
              </c:if>

              <p>
                <label>&nbsp;</label>
                <s:button name="simpanPihakKepentingan" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>
                <c:if test="${actionBean.showAddCaw}"><s:submit name="tambahCawangan" value="Tambah Cawangan" class="btn"/></c:if>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
              </p>
            </fieldset>
          </c:if>
        </c:if>

        <c:if test="${actionBean.tambahCawFlag}">
          <s:form partial="true" beanclass="etanah.view.stripes.PihakBerkepentinganActionBean">
            <s:hidden name="idPihak" value="${idPihak}"/>
            <s:hidden name="pihakCawangan.negeri.kod"/>
            <fieldset class="aras1">
              <legend>Kemasukan Cawangan Pihak Berkepentingan</legend>
              <p>
                <label for="nama"><font color="red">*</font>Nama :</label>
                    <s:text name="pihakCawangan.namaCawangan" id="nama" size="40"/>
              </p>
              <p>
                <label for="alamat">Alamat Berdaftar :</label>
                <s:text name="pihakCawangan.alamat1" id="cawanganAlamat1" size="60" style="text-transform:uppercase"/>
              </p>

              <p>
                <label> &nbsp;</label>
                <s:text name="pihakCawangan.alamat2" id="cawanganAlamat2" size="60" style="text-transform:uppercase"/>
              </p>

              <p>
                <label> &nbsp;</label>
                <s:text name="pihakCawangan.alamat3" id="cawanganAlamat3" size="60" style="text-transform:uppercase"/>
              </p>
              <p>
                <label>Bandar :</label>
                <s:text name="pihakCawangan.alamat4" id="cawanganAlamat4" size="60" style="text-transform:uppercase"/>
              <p>
                <label>Poskod :</label>
                <s:text name="pihakCawangan.poskod" id="cawanganPoskod" size="40" maxlength="5"/>
              </p>

              <p>
                <label for="Negeri">Negeri :</label>
                <s:select name="pihakCawangan.negeri.kod" id="cawanganNegeri">
                  <s:option value="">Sila Pilih</s:option>
                  <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                </s:select>

              </p>

              <p> <label><input type="checkbox" id="add2" name="add2" value="" onclick="copyAddCaw();"/></label>
                <font color="red">*Klik Untuk Salin Alamat Ke Alamat Surat Menyurat </font>
              </p>
              <p>
                <label for="alamat">Alamat Surat-Menyurat:</label>
                <s:text name="pihakCawangan.suratAlamat1" id="suratCawanganAlamat1" size="60" style="text-transform:uppercase"/>
              </p>
              <p>
                <label> &nbsp;</label>
                <s:text name="pihakCawangan.suratAlamat2" id="suratCawanganAlamat2" size="60" style="text-transform:uppercase"/>
              </p>
              <p>
                <label> &nbsp;</label>
                <s:text name="pihakCawangan.suratAlamat3" id="suratCawanganAlamat3" size="60" style="text-transform:uppercase"/>
              </p>
              <p>
                <label>Bandar :</label>
                <s:text name="pihakCawangan.suratAlamat4" id="suratCawanganAlamat4" size="60" style="text-transform:uppercase"/>
              </p>
              <p>
                <label>Poskod :</label>
                <s:text name="pihakCawangan.suratPoskod" id="suratCawanganPoskod" size="40" maxlength="5"/>
              </p>

              <p>
                <label for="Negeri">Negeri :</label>
                <s:select name="pihakCawangan.suratNegeri.kod" id="suratCawanganNegeri">
                  <s:option value="">Sila Pilih</s:option>
                  <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                </s:select>
              </p>

              <p>
                <label for="alamat">Nombor Telefon:</label>
                <s:text name="pihakCawangan.noTelefon1" size="40"/>
              </p>

              <p>
                <label>&nbsp;</label>
                <s:submit name="simpanCawangan" value="Simpan" id="SimpanCawangan" class="btn"/>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
              </p>
              <br>
            </fieldset>
          </s:form>
        </c:if>

      </s:form>
    </div>
  </body>
</html>