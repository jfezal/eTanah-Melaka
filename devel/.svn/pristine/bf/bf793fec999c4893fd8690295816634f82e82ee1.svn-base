<%--
    Document   : kemasukan_maklumat_asas
    Created on : 21 Oktober 2009, 3:36:26 PM
    Author     : khairil
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%--<script type="text/javascript"
            src="<%= request.getContextPath()%>/scripts/jquery-1.3.2.min.js"></script>--%>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery.bestupper.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/fraction.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/date.format.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<style type="text/css">
    .origWidth {
        width:150px;
    }
</style>
<script type="text/javascript">
    $(document).ready(function () {
        alert('Sila Pastikan Pengguna Mengisikan Tarikh Daftar Asal Dan Ruang Yang disediakan!\n\
Pastikan Klik Butang "KIRA" Untuk Membuat Pengiraan Pada Tempoh Pajakan Sebelum Simpan');
        $(".datepicker1").datepicker({changeYear: true});
        $(".uppercase").bestupper();
        $(".wideselect")
                .mouseover(function () {
                    $(this).data("origWidth", $(this).css("width")).css("width", "auto");
                })
                .mouseout(function () {
                    $(this).css("width", $(this).data("origWidth"));
                });

        $("#noPu").val($("#hiddenNoPu").val());
        $("#syaratNyata").val($("#hiddenSyaratNyata").val());
        $("#sekatan").val($("#hiddenKodSekatan").val());
        //alert($("#sekatan").val());
        $("#noSyit").val($("#hiddenNoSyit").val());
        $("#lokasi").val($("#hiddenLokasi").val());

        $("#cariKodSyaratNyata").click(function () {
            var url = "${pageContext.request.contextPath}/pendaftaran/maklumat_asas?showFormCariKodSyaratNyata";
            window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,width=900,height=600");
        });

        $("#cariKodSekatan").click(function () {
            var url = "${pageContext.request.contextPath}/pendaftaran/maklumat_asas?showFormCariKodSekatan";
            window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=600");
        });

        if ($("#pegangan").val() == 'S') {
            $("#tempohPegangan").val('');
            $("#tempoh").hide();
            $("#tarikhLuput").hide();
            //$("#tempohPegangan").attr("disabled", true);
        }
        $("#pegangan").change(function () {
            if ($("#pegangan").val() == 'S') {
                $("#tempohPegangan").val('');
                $("#tempoh").hide();
                $("#tarikhLuput").hide();
                //$("#tempohPegangan").attr("disabled", true);
            }
            if ($("#pegangan").val() == 'P') {
                $("#tempohPegangan").val($("#tempohP").val());
                //$("#tempohPegangan").attr("disabled", false);
                $("#tempoh").show();
                $("#tarikhLuput").show();
            }
        });

    });

    function doCalcEndDate(id) {
        var thn = parseInt($('#tempohPegangan').val(), 10);
        if ($('#' + id).val() == '') {
            return;
        }

        if (isNaN(thn)) {
            alert('Sila Masukan Tempoh.');
            $('#tkhTamat').val('');
            return;
        }

        if (thn == '0')
            return;
        var startDate = $('#' + id).val();

        //manual process :: should find conclusion on this
        var y = parseInt(startDate.substring(6, 10), 10);
        var m = parseInt(startDate.substring(3, 5), 10);
        var d = parseInt(startDate.substring(0, 2), 10);

        if (!isNaN(thn)) {
            y = y + thn;
        }

        var endDate = new Date();
        var s = 1;
        endDate.setDate(d);
        endDate.setMonth(m - 1);
        endDate.setFullYear(y);
        endDate.setDate(endDate.getDate() - s);
        $('#tkhTamat').val(endDate.format("dd/mm/yyyy"));
        document.getElementById('tPegangan').value = $('#tempohPegangan').val();
    }

    function doCalcEndDateWithoutTarikhDaftarAsal() {
        var thn = parseInt($('#tempohPegangan').val(), 10);

        if (isNaN(thn)) {
            alert('Sila Masukan Tempoh.');
            $('#tkhTamat').val('');
            return;
        }

        if (thn == '0')
            return;
        var today = new Date();
        var startDate = today.format("dd/mm/yyyy");

        //manual process :: should find conclusion on this
        var y = parseInt(startDate.substring(6, 10), 10);
        var m = parseInt(startDate.substring(3, 5), 10);
        var d = parseInt(startDate.substring(0, 2), 10);

        if (!isNaN(thn)) {
            y = y + thn;
        }

        var endDate = new Date();
        var s = 1;
        endDate.setDate(d);
        endDate.setMonth(m - 1);
        endDate.setFullYear(y);
        endDate.setDate(endDate.getDate() - s);
        $('#tkhTamat').val(endDate.format("dd/mm/yyyy"));
        document.getElementById('tPegangan').value = $('#tempohPegangan').val();
    }

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.MaklumatAsasActionBean" id="maklumatAsasForm">
    <s:messages/>
    <s:errors />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Asas</legend>
            <s:hidden name="tarikhDaftar"/>
            <s:hidden name="tPegangan" id="tPegangan" value=""/>
            <%-- <p>
                 <label>Tarikh Daftar :</label>
                 <fmt:formatDate type="date"
                                 pattern="dd/MM/yyyy"
                                 value="${actionBean.p.senaraiHakmilik[0].hakmilik.tarikhDaftar}"/>&nbsp;
                 <s:hidden name="tarikhDaftar"/>
                  <s:text name="tarikhDaftar" class="datepicker" value="${actionBean.p.senaraiHakmilik[0].hakmilik.tarikhDaftar}"  formatType="date"/>
             </p>--%>
            <%-- <p>
                 <label>Jenis Hakmilik :</label>
                 <s:select class="wideselect" name="hakmilik.kodHakmilik.kod" id="jenisHakmilik" value="${actionBean.p.senaraiHakmilik[0].hakmilik.kodHakmilik.kod}">
                     <s:option value="">-- Sila Pilih --</s:option>
                     <s:options-collection collection="${list.senaraiKodHakmilik}" label="nama" value="kod" />
                 </s:select>
             </p>
            --%>
            <%-- <p>
                 <label>Kategori :</label>
                 <s:select name="hakmilik.kategoriTanah.kod" value="${actionBean.p.senaraiHakmilik[0].hakmilik.kategoriTanah.kod}">
                     <s:option value="">-- Sila Pilih --</s:option>
                     <s:options-collection collection="${list.senaraiKodKategoriTanah}" label="nama" value="kod" />
                 </s:select>
             </p>--%>
            <%--<p>
                <label>Tanah Rezab :</label>
                <s:select  style="text-transform:uppercase" class="wideselect" name="hakmilik.rizab.kod" value="${actionBean.p.senaraiHakmilik[0].hakmilik.rizab.kod}">
                    <s:option value="">Tidak</s:option>
                    <s:options-collection collection="${list.senaraiKodRizab}" label="nama" value="kod" />
                </s:select>
            </p>

            <p>
                <label>Tarikh Warta Rezab</label>
                <s:text name="trh_warta_rezab" class="datepicker"/>
            </p>
            <p>
                <label>No Warta Rezab</label>
                <s:text name="hakmilik.rizabNoWarta" />
            </p>
            <p><label>Kawasan Rezab</label>
                <s:text name="hakmilik.rizabKawasan" />
            </p>
            <p>
                <label>Pihak Berkuasa Tempatan :</label>

                <s:select style="text-transform:uppercase" class="wideselect" name="hakmilik.pbt.kod" value="${actionBean.p.senaraiHakmilik[0].hakmilik.pbt.kod}">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${actionBean.senaraiKodPBT}" label="nama" value="kod" />
                </s:select>
            </p>--%>
            <c:if test="${actionBean.p.kodUrusan.kod ne 'HSBM' && actionBean.p.kodUrusan.kod ne 'HKBM' 
                          && actionBean.p.kodUrusan.kod ne 'BMSTM' 
                          && actionBean.p.kodUrusan.kod ne 'HKTHK' && actionBean.p.kodUrusan.kod ne 'HSTHK' }">
                  <p>
                      <label>Tarikh Daftar Asal :</label>
                      <%--<fmt:formatDate type="date"
                                      pattern="dd/MM/yyyy"
                                      value="${actionBean.p.senaraiHakmilik[0].hakmilik.tarikhDaftarAsal}"/>&nbsp;--%>
                      <%--<s:hidden name="tarikhDaftarAsal"/>--%>
                      <s:text name="tarikhDaftarAsal" class="datepicker" id="tarikhDaftarAsal"
                              value="${actionBean.p.senaraiHakmilik[0].hakmilik.tarikhDaftarAsal}"  formatType="date" size="20"/> 
                  </p>
            </c:if>
            <c:if test="${actionBean.p.kodUrusan.kod eq 'HSP' || actionBean.p.kodUrusan.kod eq 'HKP'
                          || actionBean.p.kodUrusan.kod eq 'HSPB' || actionBean.p.kodUrusan.kod eq 'HKPB'}">
                <c:set var="disabledTempoh" value="disabled"/>
                <s:hidden name="p.senaraiHakmilik[0].hakmilik.pegangan" value="${actionBean.p.senaraiHakmilik[0].hakmilik.pegangan}"/>
                <s:hidden name="tempohPegangan" value="${actionBean.p.senaraiHakmilik[0].hakmilik.tempohPegangan}" />
            </c:if>
            <p>
                <label>Taraf Pegangan :</label>
                <!--comment out disable function by azri : for new request (20/3/2013)-->
                <%--<s:select style="text-transform:uppercase" name="p.senaraiHakmilik[0].hakmilik.pegangan" id="pegangan" value="${actionBean.p.senaraiHakmilik[0].hakmilik.pegangan}" disabled="${disabledTempoh}">--%>
                <s:select style="text-transform:uppercase" name="p.senaraiHakmilik[0].hakmilik.pegangan" 
                          id="pegangan" value="${actionBean.p.senaraiHakmilik[0].hakmilik.pegangan}">  
                    <c:if test="${actionBean.kodJenisHakmilik ne 'GRN' && actionBean.kodJenisHakmilik ne 'GM' 
                                  && actionBean.kodJenisHakmilik ne 'GMM' && actionBean.kodJenisHakmilik ne 'HMM'
                                  && actionBean.kodJenisHakmilik ne 'GRS' && actionBean.kodJenisHakmilik ne 'PNS'}">
                        <s:option value="P">Pajakan</s:option>
                        <s:option value="S">Selama - lamanya</s:option>
                    </c:if>
                    <c:if test="${actionBean.kodJenisHakmilik eq 'GRN' || actionBean.kodJenisHakmilik eq 'GM' 
                                  || actionBean.kodJenisHakmilik eq 'GMM' || actionBean.kodJenisHakmilik eq 'HMM'
                                  || actionBean.kodJenisHakmilik eq 'GRS'}">
                        <s:option value="S">Selama - lamanya</s:option>
                    </c:if>
                    <c:if test="${actionBean.kodJenisHakmilik eq 'PNS'}">
                        <s:option value="P">Pajakan</s:option>
                    </c:if>
                </s:select>
            </p>
            <div id="tempoh">
                <p>
                    <label>Tempoh :</label>
                    <!--comment out disable function by azri : for new request (20/3/2013)-->
                    <%--<s:text name="tempohPegangan" id="tempohPegangan" value="${actionBean.p.senaraiHakmilik[0].hakmilik.tempohPegangan}" size="3" maxlength="3" disabled="${disabledTempoh}"/> Tahun--%>
                    <c:if test="${actionBean.p.kodUrusan.kod ne 'HSBM' && actionBean.p.kodUrusan.kod ne 'HKBM' 
                                  && actionBean.p.kodUrusan.kod ne 'BMSTM' 
                                  && actionBean.p.kodUrusan.kod ne 'HKTHK' && actionBean.p.kodUrusan.kod ne 'HSTHK' }">
                        <s:text name="tempohPegangan" id="tempohPegangan" onchange="doCalcEndDate('tarikhDaftarAsal');" 
                                value="${actionBean.p.senaraiHakmilik[0].hakmilik.tempohPegangan}" size="3" maxlength="3" /> Tahun     (Contoh : 99 tahun [asal]  + 70 tahun [tambahan] =  nilai yang perlu dimasukkan 169)  
                        <input type="hidden" value="" id="test1" class="uppercase"/>
                    </c:if>
                    <c:if test="${actionBean.p.kodUrusan.kod eq 'HSBM' || actionBean.p.kodUrusan.kod eq 'HKBM' 
                                  || actionBean.p.kodUrusan.kod eq 'BMSTM' 
                                  || actionBean.p.kodUrusan.kod eq 'HKTHK' || actionBean.p.kodUrusan.kod eq 'HSTHK' }">
                        <s:text name="tempohPegangan" id="tempohPegangan" onchange="doCalcEndDateWithoutTarikhDaftarAsal();" 
                                value="${actionBean.p.senaraiHakmilik[0].hakmilik.tempohPegangan}" size="3" maxlength="3" /> Tahun   
                        <input type="hidden" value="" id="test1" class="uppercase"/>
                    </c:if>



                    <%-- <s:select name="hakmilik.tempohPegangan" id="tempohPegangan" value="${actionBean.p.senaraiHakmilik[0].hakmilik.tempohPegangan}">
                         <c:if test="${actionBean.kodJenisHakmilik ne 'GRN' && actionBean.kodJenisHakmilik ne 'HSD' && actionBean.kodJenisHakmilik ne 'PN'}">
                             <s:option value=''>Tiada</s:option>
                             <s:option value="66">66  Tahun</s:option>
                             <s:option value="99">99  Tahun</s:option>
                         </c:if>
                         <c:if test="${actionBean.kodJenisHakmilik eq 'GRN'}">
                             <s:option value=''>Tiada</s:option>
                         </c:if>
                         <c:if test="${actionBean.kodJenisHakmilik eq 'HSD' || actionBean.kodJenisHakmilik eq 'PN'}">
                             <s:option value="66">66  Tahun</s:option>
                             <s:option value="99">99  Tahun</s:option>
                         </c:if>
                     </s:select>--%>
                </p>
            </div>
            <div id="tarikhLuput">
                <p>
                    <label>Tarikh Luput :</label>
                    <%-- <c:if test="${actionBean.p.senaraiHakmilik[0].hakmilik.tarikhLuput eq null}">
                         Tiada
                     </c:if>--%>
                    <s:text name="tarikhLuput" class="datepicker1" id="tkhTamat" 
                            formatPattern="dd/MM/yyyy" formatType="date"
                            value="${actionBean.p.senaraiHakmilik[0].hakmilik.tarikhLuput}" size="20"/> 
                     <s:button name="Kira" id="kira" value="kira" class="btn" onclick="" />
                    <%--<fmt:formatDate type="date"
                                    pattern="dd/MM/yyyy"
                                    value="${actionBean.p.senaraiHakmilik[0].hakmilik.tarikhLuput}"/>&nbsp;--%>
                </p>
            </div>
            <div id="showPu">
                <c:if test="${actionBean.jenisHakmilik eq 'HSM' || actionBean.jenisHakmilik eq 'HSD' }">
                    <p>
                        <label for="noPu">No Permintaan Ukur :</label>
                        <input type="hidden" value="${actionBean.p.senaraiHakmilik[0].hakmilik.noPu}" id="hiddenNoPu" class="uppercase"/>
                        <s:text name="p.senaraiHakmilik[0].hakmilik.noPu" id="noPu" value="${actionBean.p.senaraiHakmilik[0].hakmilik.noPu}"/>
                    </p>
                </c:if>
            </div>
            <%-- <c:if test="${actionBean.p.kodUrusan.kod eq 'HSSTB' || actionBean.p.kodUrusan.kod eq 'HKSTB' }">
                 <p>
                     <label>Lokasi :</label>
                     <input type="hidden" value="${actionBean.p.senaraiHakmilik[0].hakmilik.lokasi}" id="hiddenLokasi"/>
                     <s:text name="hakmilik.lokasi" id="lokasi" value="${actionBean.hakmilik.lokasi}" class="uppercase"/>
                 </p>
                 <p>
                     <label>Nombor Syit :</label>
                     <input type="hidden" value="${actionBean.p.senaraiHakmilik[0].hakmilik.noLitho}" id="hiddenNoSyit"/>
                     <s:text name="hakmilik.noLitho" id="noSyit" value="${actionBean.hakmilik.noLitho}"/>
                 </p>
                 <p>
                     <label>Syarat Nyata :</label>
                     <input type="hidden" value="${actionBean.p.senaraiHakmilik[0].hakmilik.syaratNyata.kodSyarat}" id="hiddenSyaratNyata"/>
                     <s:text name="kodSyaratNyata" id="syaratNyata" readonly="true" /><s:button name="cariKodSyaratNyata" value="Cari" id="cariKodSyaratNyata" class="btn" />
                 </p>
             </c:if>--%>
            <%-- <p>
                 <label>Sekatan Kepentingan :</label>
                 <input type="hidden" value="${actionBean.p.senaraiHakmilik[0].hakmilik.sekatanKepentingan.kodSekatan}" id="hiddenKodSekatan" />
                 <s:text name="kodSekatan" id="sekatan" readonly="true" /><s:button name="cariKodSekatan" value="Cari" id="cariKodSekatan" class="btn" />
             </p>--%>
            <p>
                <label>&nbsp;</label>
                <s:button name="simpan" id="simpan" value="Simpan" class="btn" onclick=" doSubmit(this.form, this.name, 'page_div');" />
            </p>
        </fieldset>
    </div>
</s:form>