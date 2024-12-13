<%--
    Document   : testing1
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : wan.fairul
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<style type="text/css">


    #tdLabel {
        color:black;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:250px;
        margin-right:0.5em;
        text-align:right;
        width:30em;
        vertical-align:top;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:40em;
    }
</style>
<script type="text/javascript">
     function tambah(){
        alert("f");

 <%--       if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/strata/rayuankertas?tambah&bil='+f;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }--%>
    }
</script>


<s:form beanclass="etanah.view.strata.KertasPertimbanganRayuanActionBean">
    <s:messages/>
    <s:errors/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>
            <div class="content" align="center">
                <table border="0" width="80%">
                    <tr><td align="center"><b>(KERTAS PERTIMBANGAN PERMOHONAN PECAH BAHAGI BANGUNAN)</b></td></tr>

                    <tr><td><b>1. TUJUAN</b></td></tr>
                    <tr><td width="100%" id="tdDisplay">Tujuan Kertas ini disediakan bagi mempertimbangkan permohonan Pecah Bahagi Bangunan daripada ${actionBean.pihak.nama}</td></tr>

                    <tr><td><b>2. RINGKASAN PERMOHONAN</b></td></tr>
                    <tr><td>
                            <table border="0" width="100%">
                                <tr><td ><b>Pemohon :</b></td>
                                    <td id="tdDisplay">${actionBean.pihak.nama} </td>
                                </tr>
                                <tr>
                                    <td>&nbsp;</td>
                                    <td id="tdDisplay"> ${actionBean.pihak.alamat1} </td>
                                </tr>
                                <tr>
                                    <td>&nbsp;</td>
                                    <td id="tdDisplay"> ${actionBean.pihak.alamat2} ${actionBean.pihak.alamat3} </td>
                                </tr>
                                <tr>
                                    <c:if test="${actionBean.pihak.alamat4 ne null}">
                                        <td>&nbsp;</td>
                                        <td id="tdDisplay"> ${actionBean.pihak.alamat4} </td>
                                    </c:if>
                                </tr>
                                <tr>
                                    <td>&nbsp;</td>
                                    <td id="tdDisplay">  ${actionBean.pihak.poskod} ${actionBean.pihak.negeri.nama}</td>
                                </tr>
                                <tr><td><b>Nombor Hakmilik :</b></td>
                                    <td id="tdDisplay"><c:if test="${actionBean.hakmilik.noHakmilik ne null}">    ${actionBean.hakmilik.noHakmilik}&nbsp; </c:if>
                                        <c:if test="${actionBean.hakmilik.noHakmilik eq null}"> Tiada Data </c:if>
                                    </td>
                                </tr>
                                <tr><td><b>Lot/PT :</b></td>
                                    <td id="tdDisplay"><c:if test="${actionBean.hakmilik.noLot ne null}">  ${actionBean.hakmilik.noLot}&nbsp;</c:if>
                                        <c:if test="${actionBean.hakmilik.noLot eq null}"> Tiada Data </c:if>
                                    </td>
                                </tr>
                                <tr><td><b>Mukim :</b></td>
                                    <td id="tdDisplay"><c:if test="${actionBean.hakmilik.bandarPekanMukim.nama ne null}">  ${actionBean.hakmilik.bandarPekanMukim.nama}&nbsp;</c:if>
                                        <c:if test="${actionBean.hakmilik.bandarPekanMukim.nama eq null}"> Tiada Data </c:if>
                                    </td>
                                </tr>
                                <tr><td><b>Pemilik Tanah Berdaftar :</b></td>
                                    <td id="tdDisplay"><c:if test="${actionBean.hakmilikPihakBerkepentingan.pihak.nama ne null}">  ${actionBean.hakmilikPihakBerkepentingan.pihak.nama}&nbsp;</c:if>
                                        <c:if test="${actionBean.hakmilikPihakBerkepentingan.pihak.nama eq null}"> Tiada Data </c:if>
                                    </td>
                                </tr>
                                <tr><td><b>Luas Tanah :</b></td>
                                    <td id="tdDisplay"><c:if test="${actionBean.hakmilik.luas ne null}"><fmt:formatNumber pattern="#,##0.0000" value="${actionBean.hakmilik.luas}"/>&nbsp;${actionBean.hakmilik.kodUnitLuas.nama}</c:if>
                                        <c:if test="${actionBean.hakmilik.luas eq null}"> Tiada Data </c:if>
                                    </td>
                                </tr>
                                <tr><td><b>Cukai :</b></td>
                                    <td id="tdDisplay"><c:if test="${actionBean.hakmilik.cukai ne null}">  ${actionBean.hakmilik.cukai}&nbsp;</c:if>
                                        <c:if test="${actionBean.hakmilik.cukai eq null}"> Tiada Data </c:if>
                                    </td>
                                </tr>
                                <tr><td><b>Taraf Pemilikan :</b></td>
                                    <td id="tdDisplay"><c:if test="${actionBean.hakmilik.kodHakmilik.nama ne null}">  ${actionBean.hakmilik.kodHakmilik.nama}&nbsp;</c:if>
                                        <c:if test="${actionBean.hakmilik.kodHakmilik.nama eq null}"> Tiada Data </c:if>
                                    </td>
                                </tr>
                                <tr><td><b>Tempoh Pajakan :</b></td>
                                    <td id="tdDisplay"><c:if test="${actionBean.hakmilik.tempohPegangan ne null}">  ${actionBean.hakmilik.tempohPegangan}&nbsp;</c:if>
                                        <c:if test="${actionBean.hakmilik.tempohPegangan eq null}"> Kekal </c:if>
                                    </td>
                                </tr>
                                <tr><td><b>Habis Tempoh :</b></td>
                                    <td id="tdDisplay"><c:if test="${actionBean.hakmilik.tarikhLuput ne null}"> <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.hakmilik.tarikhLuput}"/> </c:if>
                                        <c:if test="${actionBean.hakmilik.tarikhLuput eq null}"> Tiada Data </c:if>
                                    </td>

                                </tr>
                                <%--  <tr><td id="tdLabel">Baki Tempoh Pajakan :</td>--%>


                                <tr><td><b>Jenis Pengunaan Tanah :</b></td>
                                    <td id="tdDisplay"><c:if test="${actionBean.hakmilik.kegunaanTanah.nama ne null}"> ${actionBean.hakmilik.kegunaanTanah.nama}&nbsp; </c:if>
                                        <c:if test="${actionBean.hakmilik.kegunaanTanah.nama eq null}"> Tiada Data </c:if>
                                    </td>
                                </tr>
                                <tr><td><b>Syarat Nyata :</b></td>
                                    <td id="tdDisplay"><c:if test="${actionBean.hakmilik.syaratNyata.syarat ne null}"> ${actionBean.hakmilik.syaratNyata.syarat}&nbsp; </c:if>
                                        <c:if test="${actionBean.hakmilik.syaratNyata.syarat eq null}"> Tiada Data </c:if>
                                    </td>
                                </tr>
                                <tr><td><b>Sekatan Kepentingan :</b></td>
                                    <td id="tdDisplay">  <c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan ne null}">${actionBean.hakmilik.sekatanKepentingan.sekatan}&nbsp;</c:if>
                                        <c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan eq null}"> Tiada Data </c:if>
                                    </td>
                                </tr>
                                <tr>

                                    <c:forEach items="${actionBean.listhakmilikUrusan}" var="hk" varStatus="statusH">
                                    <tr>
                                        <td><b>Gadaian atau lien :</b></td>

                                        <td id="tdDisplay"> <c:if test="${fn:startsWith(hk.kodUrusan.kod,'GD')}">${hk.kodUrusan.nama}&nbsp;</c:if>
                                            <c:if test="${hk.kodUrusan.kod eq null}"> Tiada Data </c:if>
                                        </td>

                                    </tr>
                                    <%--
                                    <tr><td id="tdLabel">Gadaian atau lien :</td>
                                        <td id="tdDisplay"> <c:if test="${fn:contains(hk.kodUrusan.kod,'GD')}">${hk.kodUrusan.nama}&nbsp;</c:if>
                                            <c:if test="${hk.kodUrusan.kod eq null}"> Tiada Data </c:if>
                                        </td>
                                    </tr>
                                    <tr><td id="tdLabel">Pajakan :</td>
                                        <td id="tdDisplay"> <c:if test="${fn:contains(hk.kodUrusan.kod,'PJ')}">${hk.kodUrusan.nama}&nbsp;</c:if>
                                            <c:if test="${hk.kodUrusan.kod eq null}"> Tiada Data </c:if>
                                        </td>
                                    </tr>--%>
                                </c:forEach>
                                <tr><td><b>Bilangan Blok :</b></td>
                                    <td id="tdDisplay">  <c:if test="${actionBean.bil_bgnn ne null}">${actionBean.bil_bgnn}&nbsp;</c:if>
                                        <c:if test="${actionBean.bil_bgnn eq null}"> Tiada Data </c:if>
                                    </td>
                                </tr>
                                <tr><td><b>Bilangan Tingkat :</b></td>
                                    <td id="tdDisplay">  <c:if test="${actionBean.bil_tgkt ne null}">${actionBean.bil_tgkt}&nbsp;</c:if>
                                        <c:if test="${actionBean.bil_tgkt eq null}"> Tiada Data </c:if>
                                    </td>
                                </tr>
                                <tr><td><b>Bilangan Petak :</b></td>
                                    <td id="tdDisplay">  <c:if test="${actionBean.bil_petak ne null}">${actionBean.bil_petak}&nbsp;</c:if>
                                        <c:if test="${actionBean.bil_petak eq null}"> Tiada Data </c:if>
                                    </td>
                                </tr>
                                <tr><td><b>Jenis Bangunan :</b></td>
                                    <td id="tdDisplay"> <c:if test="${actionBean.mohonbngn.kekal eq 'Y'}">
                                            Kekal
                                        </c:if>
                                        <c:if test="${actionBean.mohonbngn.kekal eq 'N'}">
                                            Sementara
                                        </c:if>
                                    </td>
                                </tr>
                                <%--  <tr><td id="tdLabel">Kelulusan CF :</td>
                                  </tr>--%>
                                <tr><td><b>Bilangan Hakmilik Strata di Pohon :</b></td>
                                    <td>  <c:if test="${actionBean.bil_petak ne null}">${actionBean.bil_petak}&nbsp;</c:if>
                                        <c:if test="${actionBean.bil_petak eq null}"> Tiada Data </c:if>
                                    </td>
                                </tr>
                                <tr><td><b>Bilangan Petak Aksesori :</b></td>
                                    <td id="tdDisplay">  <c:if test="${actionBean.bil_ptkAksr ne null}">${actionBean.bil_ptkAksr}&nbsp;</c:if>
                                        <c:if test="${actionBean.bil_ptkAksr eq null}"> Tiada Data </c:if>
                                    </td>
                                </tr>
                                <%--  <tr><td id="tdLabel">Harta Bersama :</td>
                                  </tr>
                                  <tr><td id="tdLabel">Permit Ruang Udara :</td>
                                  </tr>--%>
                                <tr><td><b> Jumlah Keseluruhan Unit Syer :</b></td>
                                    <td id="tdDisplay">  <c:if test="${actionBean.jumlah ne null}">${actionBean.jumlah}&nbsp;</c:if>
                                        <c:if test="${actionBean.jumlah eq null}"> 0 </c:if>
                                    </td>
                                </tr>
                                <%-- <tr><td id="tdLabel">Tarikh Memohon Pra Strata :</td>
                                 </tr>
                                 <tr><td id="tdLabel">Tarikh Memohon Strata Lengkap :</td>
                                 </tr>--%>

                            </table>


                            <c:if test="${edit}">
                        <tr><td><b>3. ULASAN DARI JABATAN UKUR DAN PEMETAAN</b></td></tr>
                        <tr><td><s:textarea name="ulasanJabatanUkurdanPemetaan" rows="5" cols="150" class="normal_text"/>
                       <%--         <table border="0" width="100%">
                                    <c:forEach items="${actionBean.listKand3}" var="hk" varStatus="statusH">
                                    <tr ><td>ss</td><td><s:textarea name="ulasanJabatanUkurdanPemetaan" rows="5" cols="150" class="normal_text"/></td></tr>
                                    </c:forEach>
                                    <tr><td></td><td>
                                            <s:button name="tambah" id="save" value="Tambah" class="btn" onclick="tambah()"/>
                <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </td></tr>
                                </table>--%>

                            </td></tr>
                        <tr><td><b>4. ASAS-ASAS PERTIMBANGAN</b></td></tr>
                        <tr><td> <s:textarea name="asaspertimbangan" rows="5" cols="150" class="normal_text"/>
                                <%--<table border="0" width="100%"> <c:forEach items="${actionBean.listKand4}" var="hk" varStatus="statusH">
                                    <tr ><td>ss</td><td><s:textarea name="ulasanJabatanUkurdanPemetaan" rows="5" cols="150" class="normal_text"/></td></tr>
                                    </c:forEach>
                                    <tr><td></td><td><s:button name="tambah" id="save" value="Tambah" class="btn" onclick="tambah()"/><s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                                </table>--%>
                            </td></tr>
                        <tr><td><b>5. SYOR DAN PERAKUAN TIMBALAN PENDAFTAR HAKMILIK GERAN TANAH</b></td></tr>
                        <tr><td><tr><td><s:textarea name="ulasanJabatanUkurdanPemetaan" rows="5" cols="150" class="normal_text"/>
                            <%--    <table border="0"  width="100%">  <c:forEach items="${actionBean.listKand5}" var="hk" varStatus="statusH">
                                    <tr ><td>ss</td><td><s:textarea name="ulasanJabatanUkurdanPemetaan" rows="5" cols="150" class="normal_text"/></td></tr>
                                    </c:forEach>
                                    <tr><td></td><td><s:button name="tambah" id="save" value="Tambah" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/><s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                                </table>--%>
                            </td></tr>
                                    </c:if>
                               <%--     <c:if test="${display}">
                        <tr><td><s:textarea name="ulasanJabatanUkurdanPemetaan" rows="5" cols="150" class="normal_text"/></td></tr>
                        <tr><td><b>4. ASAS-ASAS PERTIMBANGAN</b></td></tr>
                        <tr><td><s:textarea name="asaspertimbangan" rows="5" cols="150" class="normal_text"/></td></tr>
                        <tr><td><b>5. SYOR DAN PERAKUAN TIMBALAN PENDAFTAR HAKMILIK GERAN TANAH</b></td></tr>
                        <tr><td><s:textarea name="syorTP" rows="5" cols="150" class="normal_text"/></td></tr>
                    </c:if>--%>




                </table>
            </div>

            <p>
                <label>&nbsp;</label>
                <c:if test="${edit}">
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </c:if>

            </p>
        </fieldset>
    </div>

</s:form>