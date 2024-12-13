<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Utiliti Kod</title>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/pub/css/formdesign.css">
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
        <script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
        <script type="text/javascript">

            $(document).ready(function() {
                $("#senarai_kod input:text").each(function(el) {
                    $(this).blur(function() {
                        $(this).val($(this).val().toUpperCase());
                    });
                });
            });


            function validateNumber(elmnt, content) {
                //if it is character, then remove it..
                if (isNaN(content)) {
                    elmnt.value = removeNonNumeric(content);
                    return;
                }
            }

            function filterkodBPM(daerah, frm) {

//                $.post('${pageContext.request.contextPath}/utiliti/kod?senaraiKodBPMByDaerah&kodDaerah='+daerah,
//                $.post('${pageContext.request.contextPath}/common/carian_hakmilik?senaraiKodBPMByDaerah&kodDaerah='+daerah,
//                function(data){
//                    if(data != ''){
//                        $('#bpm').html(data);
//                    }
//                }, 'html');

                var url = '${pageContext.request.contextPath}/utiliti/kod?senaraiKodBPMByDaerah&kodDaerah=' + daerah;
                frm.action = url;
                frm.submit();
            }

        </script>
    </head>
    <body>
        <s:errors/>
        <s:messages/>
        <div class="subtitle">
            <s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
            <s:form beanclass="etanah.view.daftar.utiliti.UtilitiKodActionBean" name="form1">
                <fieldset class="aras1">

                    <legend>
                        Tambah Kod 
                    </legend>
                    <br>
                    <s:hidden name="table" id="table" value="${actionBean.table}"/>
                    <c:if test="${actionBean.table eq 'KodWarganegara'}">
                        <p>
                            <label>Kod :</label><s:text name="kod1" size="40" maxlength="3"/>&nbsp;
                        </p>
                    </c:if>
                    <c:if test="${actionBean.table eq 'KodKegunaanPetakAksesori' 
                                  || actionBean.table eq 'KodCawangan'}">
                          <p>
                              <label>Kod :</label><s:text name="kod1" size="10" maxlength="2"/>&nbsp;
                          </p>
                    </c:if>
                    <c:if test="${actionBean.table eq 'KodKegunaanBangunan'}">
                        <p>
                            <label>Kod :</label><s:text name="kod1" size="40" maxlength="10"/>&nbsp;
                        </p>
                    </c:if>
                    <c:if test="${actionBean.table eq 'KodKegunaanPetak'}">
                        <p>
                            <label>Kod :</label><s:text name="kod1" size="40" maxlength="7"/>&nbsp;
                        </p>
                    </c:if>
                    <c:if test="${actionBean.table eq 'KodUrusan' || actionBean.table eq 'KodDokumen'}">
                        <p>
                            <label>Kod :</label><s:text name="kod1" size="10" maxlength="5"/>&nbsp;
                        </p>
                    </c:if>
                    <c:if test="${actionBean.table eq 'KodKegunaanPetakAksesori' || actionBean.table eq 'KodKegunaanPetak'
                                  || actionBean.table eq 'KodKegunaanBangunan' || actionBean.table eq 'KodWarganegara'}">
                          <p>
                              <label>Nama :</label><s:text name="nama" class="textfield" size="40"/>&nbsp;
                          </p>
                    </c:if>
                    <c:if test="${actionBean.table eq 'KodDokumen'}">
                          <p>
                              <label>Nama :</label><s:text name="nama" class="textfield" size="40" maxlength="256"/>&nbsp;
                          </p>
                          <p>
                                <label>Kawal Capaian :</label>
                                <s:radio name="kawalCapaian" value="Y"></s:radio> Ya
                                <s:radio name="kawalCapaian" value="T" ></s:radio> Tidak
                          </p>
                          <p>
                                    <label>Penjana  :</label>
                                <s:text name="penjana" size="40" maxlength="40" />
                          </p>
                    </c:if>
                    <c:if test="${actionBean.table eq 'KodUrusan'}">
                        <p>
                            <label>Nama :</label><s:text name="nama" class="textfield" size="40" maxlength="128"/>&nbsp;
                        </p>
                        <p>
                            <label>Unit :</label>
                            <s:select name="jabatan" id="jabatan" >
                                <s:option value="">Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodJabatan}" label="nama" value="kod" />
                            </s:select>
                        </p>
                        <p>
                            <label>Jenis Serahan :</label>
                            <s:select name="kodSerah" id="kodSerah" >
                                <s:option value="">Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodPerserahanAktif}" label="nama" value="kod" />
                            </s:select>
                        </p>
                        <p>
                            <label>Rujukan Kanun (Jika ada):</label>
                            <s:text name="rujKanun" class="textfield" size="40" maxlength="256"/>&nbsp;
                        </p>
                        <p>
                            <label>Urusan Kaunter  :</label>
                            <input type="radio" name="urusanKaunter" value="Y" checked="checked" /> Ya
                            <s:radio name="urusanKaunter" value="T" ></s:radio> Tidak
                            </p>
                            <p>
                                <label>Urusan Dalaman  :</label>
                                <input type="radio" name="urusanDalam" value="Y" checked="checked" /> Ya
                            <s:radio name="urusanDalam" value="T" ></s:radio> Tidak
                            </p>
                            <p>
                                <label>Urusan Belakang Kaunter  :</label>
                                <input type="radio" name="urusanBlkgKaunter" value="Y" checked="checked"> Ya
                            <s:radio name="urusanBlkgKaunter" value="T" ></s:radio> Tidak
                            </p>
                            <p>
                                <label>Urusan Bayaran  :</label>
                            <s:radio name="urusanBayar" value="Y" ></s:radio> Ya
                                <input type="radio" name="urusanBayar" value="T" checked="checked"> Tidak
                            </p>
                            <p>
                                <label>Kod Transaksi :</label>
                            <s:select name="kodTrans" id="kodTrans" >
                                <s:option value="">Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodTransaksiAktif}" label="nama" value="kod" />
                            </s:select>
                        </p>
                        <p>
                            <label>Kategori Bayaran (Jika ada):</label>
                            <s:select name="kodKatgBayar" id="kodKatgBayar" >
                                <s:option value="">Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodKategoriBayaranAktif}" label="nama" value="kod" />
                            </s:select>
                        </p>
                        <p>
                            <label>Caj Bayaran :</label>
                            <s:text name="caj" class="textfield" size="10"/>
                        </p>
                        <!--                        <p>
                                                    <label>Caj Bayaran Tambahan (Jika Ada):</label>
                        <s:text name="cajTambahan" class="textfield" size="10"/>
                    </p>-->
                        <p>
                            <label>Id Workflow :</label>
                            <s:text name="idWorkflow" class="textfield" size="40" maxlength="250"/>
                        </p>
                        <p>
                            <label>Jelaskan Cukai :</label>
                            <input type="radio" name="cukai" value="Y" checked="checked"/> Ya
                            <s:radio name="cukai" value="T" ></s:radio> Tidak
                            </p>
                            <p>
                                <label>Ke PTG :</label>
                            <s:radio name="ptg" value="Y"></s:radio> Ya
                                <input type="radio" name="ptg" value="T" checked="checked"/> Tidak
                            </p>
                            <p>
                                <label>Bil Keutamaan (hari) :</label>
                            <s:text name="utama" class="textfield" size="10" maxlength="1"/>
                        </p>
                        <p>
                            <label>Bil Sasaran Bulanan :</label>
                            <s:text name="sasarBulan" class="textfield" size="10" maxlength="2"/>
                        </p>
                        <p>
                            <label>Minumum Bilangan Hakmilik :</label>
                            <s:text name="minHakmilik" class="textfield" size="10" maxlength="3"/>
                        </p>
                        <p>
                            <label>Maximum Bilangan Hakmilik :</label>
                            <s:text name="maxHakmilik" class="textfield" size="10" maxlength="3"/>
                        </p>
                        <p>
                            <label>Id Workflow Integrasi (Jika Ada):</label>
                            <s:text name="idWorkflowIntegration" class="textfield" size="40" maxlength="250"/>
                        </p>
                    </c:if>


                    <c:if test="${actionBean.table eq 'KodKadarBayaran'}">
                        <p>
                            <label>Nama Urusan :</label>
                            <s:select name="kodUrusan" id="kodUrusan" >
                                <s:option value="">Pilih</s:option>
                                <%--<s:options-collection collection="${list.senaraiKodUrusanAktif}" label="nama" value="kod" />--%>
                                <c:forEach items="${list.senaraiKodUrusanAktif}" var="items">
                                    <s:option value="${items.kod}">${items.kod} - ${items.nama}</s:option>                            
                                </c:forEach>
                            </s:select>
                        </p>
                        <p>
                            <label>Milik Daerah :</label>
                            <s:radio name="milikDaerah" value="Y"></s:radio> Ya
                            <s:radio name="milikDaerah" value="T" ></s:radio> Tidak
                            </p>
                            <p>
                                <label>Kategori :</label>
                            <s:text name="katg" class="textfield" size="10" maxlength="30"/>
                        </p>
                        <p>
                            <label>Nilai Minimum :</label>
                            <s:text name="min" class="textfield" size="10" maxlength="12"/>
                        </p>
                        <p>
                            <label>Nilai Maximum :</label>
                            <s:text name="max" class="textfield" size="10" maxlength="12"/>
                        </p>
                        <p>
                            <label>Bil. Hakmilik :</label>
                            <s:text name="bilHakmilik" class="textfield" size="10" maxlength="2"/>
                        </p>
                        <p>
                            <label>Kadar Bayaran :</label>
                            <s:text name="kadar" class="textfield" size="10" maxlength="12"/>
                        </p>
                    </c:if>
                    <c:if test="${actionBean.table eq 'KodDaerah'}">
                        <p>
                            <label>Kod Daerah :</label><s:text name="kod1" class="textfield" size="10" maxlength="2"/>&nbsp;
                        </p>
                        <p>
                            <label>Nama Daerah :</label><s:text name="nama" class="textfield" size="40" maxlength="50"/>&nbsp;
                        </p>
                    </c:if>
                    <c:if test="${actionBean.table eq 'KodBandarPekanMukim'}">
                        <p>
                            <label>Kod Daerah :</label>&nbsp;
                            <s:select name="daerah" id="daerah" >
                                <s:option value="">Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodDaerah}" label="nama" value="kod" />
                            </s:select>
                        </p>
                        <p>
                            <label>Kod Cawangan :</label>&nbsp;
                            <s:select name="cawangan" id="cawangan" >
                                <s:option value="">Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodCawanganAktif}" label="name" value="kod" />
                            </s:select>
                        </p>
                        <p>
                            <label>Kod BPM :</label><s:text name="kodBPM" class="textfield" size="10" maxlength="2"/>&nbsp;
                        </p>
                        <p>
                            <label>Nama Kod BPM :</label><s:text name="nama" class="textfield" size="40" maxlength="50"/>&nbsp;
                        </p>
                        <!--                        <p>
                                                    <label>Kod Notis :</label><s:text name="namaKodBPM" class="textfield" size="40" maxlength="4"/>&nbsp;
                                                </p>-->
                    </c:if>
                    <c:if test="${actionBean.table eq 'KodBank'}">
                        <p>
                            <label>Kod :</label><s:text name="kodBankBaru" size="40"/>&nbsp;
                        </p>
                        <p>
                            <label>Nama Bank :</label><s:text name="nama" size="40"/>&nbsp;
                        </p>
                        <p>
                            <label>Kod Cawangan :</label>
                            <s:select name="o.cawangan.kod">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodCawangan}" label="name" value="kod"/>
                            </s:select>
                        </p>
                        <p>
                            <label>Aktif  :</label>
                            <s:radio name="aktif" value="Y"></s:radio> Ya
                            <s:radio name="aktif" value="T" ></s:radio> Tidak
                            </p>
                    </c:if>
                    <c:if test="${actionBean.table eq 'KodTransaksi'}">
                        <p>
                            <label>Kod :</label><s:text name="kodTransBaru" size="10"/>&nbsp;
                        </p>
                        <p>
                            <label>Nama :</label><s:text name="namaTrans" size="40"/>&nbsp;
                        </p>
                        <p>
                            <label>Perihal :</label><s:text name="perihal" size="40"/>&nbsp;
                        </p>
                        <p>
                            <label>akaun Debit :</label>
                            <s:select name="akaunDebit" id="akaunDebit" >
                                <s:option value="">Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodAkaun}" label="nama" value="kod" />
                            </s:select>

                        </p>
                        <p>
                            <label>akaun Kredit:</label>
                            <s:select name="akaunKredit" id="akaunKredit" >
                                <s:option value="">Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodAkaun}" label="nama" value="kod" />
                            </s:select>
                        </p>
                        <p>
                            <label>keutamaan :</label><s:text name="keutamaan" size="5"/>&nbsp;
                        </p>
                        <p>
                            <label>Persekutuan  :</label>
                            <s:radio name="persekutuan" value="Y"></s:radio> Ya
                            <s:radio name="persekutuan" value="T" ></s:radio> Tidak
                            </p>
                            <p>
                                <label>Transaksi Amanah  :</label>
                            <s:radio name="transaksiAmanah" value="Y"></s:radio> Ya
                            <s:radio name="transaksiAmanah" value="T" ></s:radio> Tidak
                            </p>
                    </c:if>
                    <c:if test="${actionBean.table eq 'KodSeksyen'}">
                        <p>
                            <label>Kod Daerah :</label>&nbsp;
                            <s:select name="daerah" id="daerah" onchange="filterkodBPM(this.value,this.form);">
                                <s:option value="">Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodDaerah}" label="nama" value="kod" />
                            </s:select>
                        </p>
                        <p>
                            <label>Kod BPM :</label>&nbsp;
                            <s:select name="bpm" id="bpm" >
                                <s:option value="">Pilih</s:option>
                                <s:options-collection collection="${actionBean.listBandarPekanMukim}" label="nama" value="kod" />
                            </s:select>
                        </p>
                        <p>
                            <label>Kod Seksyen :</label><s:text name="kodSeksyen" class="textfield" size="10" maxlength="3"/>&nbsp;
                        </p>
                        <p>
                            <label>Nama Kod Seksyen :</label><s:text name="nama" class="textfield" size="40" maxlength="50"/>&nbsp;
                        </p>
                    </c:if>
                    <c:if test="${actionBean.table eq 'KodKategoriTanah'}">
                        <p>
                            <label>Nama :</label><s:text name="nama" class="nama" size="40" maxlength="64"/>&nbsp;
                        </p>
                        <p>
                            <label>Kod MyEtapp (jika ada) :</label><s:text name="etapp" class="textfield" size="40" maxlength="5"/>&nbsp;
                        </p>
                    </c:if>
                    <c:if test="${actionBean.table eq 'KodKegunaanTanah'}">
                        <p>
                            <label>Kod :</label><s:text name="kod1" class="textfield" size="40" maxlength="3"/>&nbsp;
                        </p>
                        <p>
                            <label>Kategori Tanah :</label>&nbsp;
                            <s:select name="katg" id="katg" >
                                <s:option value="">Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodKategoriTanah}" label="nama" value="kod" />
                            </s:select>
                        </p>
                        <p>
                            <label>Nama :</label><s:text name="nama" class="textfield" size="40" maxlength="100"/>&nbsp;
                        </p>
                        <p>
                            <label>Kod MyEtapp (jika ada) :</label><s:text name="etapp" class="textfield" size="40" maxlength="5"/>&nbsp;
                        </p>
                        <p>
                            <label>Kod Cukai (jika ada) :</label><s:text name="cukai" class="textfield" size="40" maxlength="3"/>&nbsp;
                        </p>
                    </c:if>
                    <c:if test="${actionBean.table eq 'KodNegeri'}">
                        <p>
                            <label>Kod :</label><s:text name="kod1" size="40" maxlength="2"/>&nbsp;
                        </p>
                        <p>
                            <label>Nama :</label><s:text name="nama" class="textfield" size="40" maxlength="32"/>&nbsp;
                        </p>
                    </c:if>
                    <c:if test="${actionBean.table eq 'KodBank'}">
                        <p>
                            <label>Kod :</label><s:text name="kod1" size="40" maxlength="10"/>&nbsp;
                        </p>
                        <p>
                            <label>Nama :</label><s:text name="nama" class="textfield" size="40" maxlength="32"/>&nbsp;
                        </p>
                    </c:if>
                    <c:if test="${actionBean.table eq 'KodPenubuhanSyarikat'}">
                        <p>
                            <label>Nama Penubuhan Syarikat :</label><s:text name="nama" class="textfield" size="40" maxlength="65"/>&nbsp;
                        </p>
                    </c:if>
                    <c:if test="${actionBean.table eq 'KodCawangan'}">
                        <p>
                            <label>Kod Daerah :</label>&nbsp;
                            <s:select name="daerah" id="daerah" >
                                <s:option value="">Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodDaerah}" label="nama" value="kod" />
                            </s:select>
                        </p>
                        <p>
                            <label>Nama :</label>&nbsp;
                            <s:text name="nama" maxlength="50" size="40" />
                        </p>
                        <br />
                        <p>
                            <label>Alamat :</label>&nbsp;<s:text name="alamat1" maxlength="60" size="30"/>
                        </p>
                        <p>
                            <label>&nbsp;</label><s:text name="alamat2" maxlength="60" size="30"/>
                        </p>
                        <p>
                            <label>&nbsp;</label><s:text name="alamat3" maxlength="60" size="30"/>
                        </p>
                        <p>
                            <label>&nbsp;</label><s:text name="alamat4" maxlength="60" size="30"/>
                        </p>

                        <p>
                            <label>Poskod :</label>&nbsp;<s:text name="poskod" maxlength="5" />
                        </p>
                        <p>
                            <label>Negeri :</label>&nbsp;
                            <s:select name="negeri" id="negeri" >
                                <s:option value="">Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod" />
                            </s:select>
                        </p>
                        <br />
                        <p>
                            <label>No Tel 1 :</label>&nbsp;
                            <s:text name="tel1" maxlength="20" />
                        </p>
                        <p>
                            <label>No Tel 2 :</label>&nbsp;
                            <s:text name="tel2" maxlength="20" />
                        </p>
                        <p>
                            <label>No Fax :</label>&nbsp;
                            <s:text name="fax" maxlength="20" />
                        </p>
                        <p>
                            <label>Kod PTJ :</label>&nbsp;
                            <s:text name="kodPTJ" maxlength="50" />
                        </p>
                        <p>
                            <label>Kod Jab. SPEK :</label>&nbsp;
                            <s:text name="kodJabatanSpek" maxlength="5" />
                        </p>
                        <br />
                        <fieldset class="aras1">
                            <legend>Muat Naik Cop</legend>
                            <p>
                                <label> Fail :</label>&nbsp;
                                <s:file name="fileToBeUpload" id="fileToBeUpload"/>
                            </p>
                            Cop 
                            <br>
                            <c:if test="${actionBean.blob ne null}">

                                <p>
                                <center>
                                    <img src ="${pageContext.request.contextPath}/uam/uploadTT?viewCop&idParam=${actionBean.kodCawangan.cop}" height="100" >

                                </center>
                                </p>
                            </c:if>
                            <c:if test="${actionBean.blob eq null}">
                                <p>
                                    <label> Cop : <font size="2" color="red">Belum Dimuat Naik</font></label> 
                                </p>
                            </c:if>
                            <br/>
                        </fieldset>
                    </c:if>
                    <c:if test="${actionBean.table eq 'KodKadarPremium'}">
                        <%--<p>
                            <label>ID Kod Kadar Premium :</label><s:text name="idKodKadarPremium" size="40" maxlength="6" readonly="readonly"/>&nbsp;
                        </p>--%>
                        <p>
                            <label>Kod Tanah :</label>
                            <s:select name="kodTanah" id="kodTanah" >
                                <s:option value="">Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodTanah}" label="nama" value="kod" />
                            </s:select>
                        </p>
                        <p>
                            <label>Kod Kegunaan Tanah :</label>
                            <s:select name="kodKegunaanTanah" id="kodKegunaanTanah" >
                                <s:option value="">Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKegunaanTanah}" label="nama" value="kod" />
                            </s:select>
                        </p>
                        <p>
                              <label>Peratus Kadar :</label><s:text name="peratusKadar" class="textfield" size="10"/>&nbsp;
                        </p>
                        <p>
                              <label>Nama :</label><s:text name="nama" class="textfield" size="40"/>&nbsp;
                        </p>
                    </c:if>
                    <c:if test="${actionBean.table eq 'UrusanDokumen'}">
                        <p>
                            <label>Kod Urusan :</label>
                            <s:select name="kodUrusan" id="kodUrusan" style="width:350px;">
                                <s:option value="">Pilih</s:option>
                                <c:forEach items="${list.senaraiKodUrusan}" var="items">
                                    <s:option value="${items.kod}" >${items.kod} - ${items.nama}</s:option>                            
                                </c:forEach>
                            </s:select>
                        </p>
                        <p>
                            <label>Kod Dokumen :</label>
                        </p>
                        <p>
                            <s:select name="kodDokumen1" id="kodDokumen1" style="width:350px;">
                                <s:option value="">Pilih</s:option>
                                <c:forEach items="${list.senaraiKodDokumen}" var="items">
                                    <s:option value="${items.kod}" >${items.kod} - ${items.nama}</s:option>                            
                                </c:forEach>
                            </s:select>
                        </p>
                        <p>
                            <label>Wajib  :</label>
                            <s:radio name="wajib" value="Y"></s:radio> Ya
                            <s:radio name="wajib" value="T"></s:radio> Tidak
                        </p>
                        <p>
                            <label>Perlu Disah  :</label>
                            <s:radio name="perluDisah" value="Y"></s:radio> Ya
                            <s:radio name="perluDisah" value="T"></s:radio> Tidak
                        </p>
                        <p>
                            <label>Perlu Diimbas :</label>
                            <s:radio name="perluDiimbas" value="Y"></s:radio> Ya
                            <s:radio name="perluDiimbas" value="T" ></s:radio> Tidak
                        </p>
                        <p>
                            <label>Catatan :</label><s:text name="catatan" class="textfield" size="10"/>&nbsp;
                        </p>
                        <p>
                            <label>Caj :</label><s:text name="caj" class="textfield" size="40"/>&nbsp;
                        </p>
                        <p>
                            <label>Kod Transaksi :</label>
                            <s:select name="kodTransaksi1" id="kodTransaksi1" style="width:350px;">
                                <s:option value="">Pilih</s:option>
                                <c:forEach items="${list.senaraiKodTransaksiAktif}" var="items">
                                    <s:option value="${items.kod}" >${items.kod} - ${items.nama}</s:option>                            
                                </c:forEach>
                            </s:select>
                        </p>
                        <p><label for="kategoriPemohon">Kategori Pemohon</label>
                                <s:select name="kategoriPemohon" id="kategoriPemohon" >
                                    <s:option value="X">X - Tidak dinyatakan</s:option>
                                    <s:option value="I">I - Individu(*)</s:option>
                                    <s:option value="O">O - Organisasi/Syarikat(**)</s:option>
                                    <s:option value="P">P - Pertubuhan(***)</s:option>
                                </s:select>
                        </p>
                    </c:if>
                    <p>
                        <label>Aktif  :</label>
                        <s:radio name="aktif" value="Y"></s:radio> Ya
                        <s:radio name="aktif" value="T" ></s:radio> Tidak
                        </p>
                        <center>
                            <br />
                        <s:submit name="simpanKod" id="simpan" value="Simpan" class="btn"/>
                        <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                    </center>
                </s:form>
                <br>
            </fieldset>
        </div>

    </body>
</html>