<%-- 
    Document   : gantian_resit_batal_2
    Created on : Nov 30, 2009, 12:20:06 PM
    Author     : nurfaizati
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<table width="100%" bgcolor="green">
    <tr>
        <td width="100%" height="20" >
            <div style="background-color: green;" align="center">
                <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">Kemasukan Semula Resit</font>
            </div>
        </td>
    </tr>
</table>
<script  language="javascript" >

    function updateTotal(inputTxt){
        var total = 0;
        for (var i = 0; i <5; i++){
            var a = document.getElementById('amaun' + i)
            if (isNaN(a.value)){
                alert("Nombor tidak sah");
                return;
            }
            total += parseFloat(a.value);
        }
        var t = document.getElementById('jumCaraBayar');
        t.value = total.toFixed(2);
        inputTxt.value = parseFloat(inputTxt.value).toFixed(2);
    }
</script>
<script  language="javascript" >



    function change(){
        for (var i = 0; i < 5; i++){
            var a = document.getElementById('senaraiCaraBayaran'+i);
            if (a.value == 'T'){
                $('#hapus'+i).hide();
                $("#hapus1"+i).hide();
                $("#hapus2"+i).hide();
            }
            else{
                $('#hapus'+i).show();
                $('#hapus1'+i).show();
                $('#hapus2'+i).show();
            }
        }}

</script>
<div class="subtitle">

    <s:form beanclass="etanah.view.stripes.hasil.KutipanHasilActionBean">
        <s:hidden name ="dokumenKewangan.idDokumenKewangan"/>

        <s:hidden name ="hakmilik.idHakmilik"/>

        <%--
             <fieldset class="aras1">
                 <legend>Maklumat Kutipan Yang Telah Dibatalkan</legend>



            <p>
                <label  for="No Resit">No Resit :</label>
                <s:text name="dokumenKewangan.idDokumenKewangan" size="30"/>
                ${actionBean.dokumenKewangan.idDokumenKewangan}&nbsp;
                ${actionBean.idDokumenKewangan}&nbsp;

            </p>
               <p>
                   <label>ID Hakmilik :</label>
                   ${actionBean.idHakmilik}&nbsp;
               </p>

            <p>
                <label>Tarikh Batal :</label>
                <fmt:formatDate value="${actionBean.dokumenKewangan.tarikhBatal}" pattern="dd/MM/yyyy"/>&nbsp;
            </p>
            <p>
                <label>Jumlah Kutipan (RM) :</label>
                <fmt:formatNumber value="${actionBean.jumlahCb}" pattern="0.00"/>&nbsp;
            </p>
            <p>
                <label>Sebab Batal :</label>
                ${actionBean.dokumenKewangan.kodBatal.nama}&nbsp;
            </p>

            <p>

                <label>Catatan Pembatalan Resit:</label>
                ${actionBean.dokumenKewangan.catatan}&nbsp;
                ${actionBean.dokumenKewangan.catatan}&nbsp;
            </p>
        --%>
        <br>

        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Kutipan Yang Telah Dibatalkan
                </legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.transList}" pagesize="4" cellpadding="0" cellspacing="0" id="line" requestURI="/hasil/kutipan_hasil">


                         <display:column title="Bil" sortable="true"><div align="center">${line_rowNum}</display:column>
                        <display:column  title="Jenis Transaksi" >
                            ${line.kodTransaksi.kod} - ${line.kodTransaksi.nama}</display:column>
                        <display:column property="dokumenKewangan.idDokumenKewangan" title="Nombor Resit" />
                         <c:if test="${line.permohonan.idPermohonan ne null}">
                            <display:column title="ID Permohonan">${line.permohonan.idPermohonan}</display:column>
                        </c:if>
                        <%--<display:column property="permohonan.idPermohonan" title="ID Permohonan" />--%>
                        
                        <c:if test="${line.akaunKredit.hakmilik.idHakmilik ne null}">
                            <display:column title="No Hakmilik">${line.akaunKredit.hakmilik.idHakmilik}</display:column>
                        </c:if>
                        <c:if test="${line.akaunDebit.hakmilik.idHakmilik ne null}">
                            <display:column title="No Hakmilik">${line.akaunDebit.hakmilik.idHakmilik}</display:column>
                        </c:if>
                        <display:column  title="Sebab Batal" >
                            ${line.dokumenKewangan.kodBatal.nama} : ${line.dokumenKewangan.catatan}</display:column>

                        <display:column property="dokumenKewangan.status.nama" title="Status"  />
                        <display:column property="dokumenKewangan.tarikhBatal" title="Tarikh Batal"  format="{0,date,dd/MM/yyyy}"  />

                    </display:table>
                </div>

            </fieldset>
        </div>

        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Senarai Urusan Bayaran Terdahulu
                </legend>
                <div class="content" align="center">

                    <display:table class="tablecloth" name="${actionBean.transList}" pagesize="10" cellpadding="0" cellspacing="0" id="line" requestURI="/hasil/kutipan_hasil">
                        <%--<display:table class="tablecloth" name="${actionBean.tempList}" pagesize="10" cellpadding="0" cellspacing="0" id="line" requestURI="/hasil/kutipan_hasil">--%>

                        <display:column title="Bil" sortable="true"><div align="center">${line_rowNum}</display:column>
                        <%--<display:column property="dokumenKewangan.senaraiBayaran.idKewanganBayaran" title="No Akaun/ID Perserahan"/>--%>
                        <%--<display:column property="akaunKredit.noAkaun" title="No Akaun"/>akaunKredit.hakmilik.idHakmilik--%>
                        <%--<display:column property="akaunKredit.hakmilik.idHakmilik" title="No Akaun"/>--%>

                        <c:if test="${line.akaunKredit.hakmilik.idHakmilik ne null}">
                            <display:column title="ID Hakmilik">${line.akaunKredit.hakmilik.idHakmilik}</display:column>
                        </c:if>
                        <c:if test="${line.akaunDebit.hakmilik.idHakmilik ne null}">
                            <display:column title="ID Hakmilik">${line.akaunDebit.hakmilik.idHakmilik}</display:column>
                        </c:if>

                        <%--                    <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                                      <c:if test="${line.akaunKredit.hakmilik.idHakmilik ne null}">
                                          <display:column title="No Akaun">${line.akaunKredit.noAkaun}</display:column>
                                      </c:if>
                                      <c:if test="${line.akaunDebit.hakmilik.idHakmilik ne null}">
                                          <display:column title="No Akaun">${line.akaunDebit.noAkuan}</display:column>
                                      </c:if>
                                       </c:if>
                        --%>
                        <%--<display:column property="dokumenKewangan.idDokumenKewangan" title="Status" />--%>

                        <display:column  title="Jenis Transaksi" >
                            ${line.kodTransaksi.kod} - ${line.kodTransaksi.nama}</display:column>
                        <%--<display:column property="dokumenKewangan.idDokumenKewangan" title=" Nombor Resit"  />--%>
                        <display:column title="Tarikh Transaksi" format="{0,date,dd/MM/yyyy}" style="text-align:center" property="infoAudit.tarikhMasuk"/>
                        <display:column property="status.nama" title="Status" />

                        <display:column title="Cara Bayaran">
                            <c:forEach items="${line.dokumenKewangan.senaraiBayaran}" var="senarai">
                                <c:out value="${senarai.caraBayaran.kodCaraBayaran.nama}"/><br>
                            </c:forEach>
                        </display:column>
                        <display:column title="Bank / Agensi Pembayaran">
                            <c:forEach items="${line.dokumenKewangan.senaraiBayaran}" var="senarai">
                                <c:out value="${senarai.caraBayaran.bank.nama}"/><br>
                            </c:forEach>
                        </display:column>
                        <display:column title="No Rujukan">
                            <c:forEach items="${line.dokumenKewangan.senaraiBayaran}" var="senarai">
                                <c:out value="${senarai.caraBayaran.noRujukan}"/><br>
                            </c:forEach>
                        </display:column>

                        <display:column property="amaun" title="Jumlah (RM)" format="{0,number, 0.00}" style="text-align:right"/>

                        <display:footer>
                            <tr>
                       <td colspan="7" align="right"><div align="right"><b>Jumlah (RM) : </b></div></td>
                                <td  colspan="8" class="number" align="right"><div align="right"> <fmt:formatNumber  value="${actionBean.jumlahCb}" pattern="0.00"/></div></td>
                            </tr>
                        </display:footer>--%>
                    </display:table>
                </div>
            </fieldset>
        </div>

        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Cara Bayaran</legend>
                <p class=instr><em><font color="black">Masukkan butir-butir pembayaran.<br>&nbsp;
                            <font color="red">PERINGATAN:</font> Tidak dibenarkan menggunakan cara pembayaran yang lain bersama dengan pembayaran menggunakan Cek ,
                            Deraf Bank dan Wang dalam Pindahan.</font></em>
                </p>
                &nbsp;&nbsp;&nbsp;&nbsp;<s:errors field="amaun"/>
                <div align="center">
                    <table>
                        <tr>
                            <td align="right">
                                <s:submit name="addCaraBayar" value="Tambah" class="btn"/>
                                <s:button name=" " value="Isi Semula" class="btn" onclick="clearText1('hasil');"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <display:table name="${actionBean.senaraiCaraBayaran}" id="row" class="tablecloth">
                                    <display:column title="Bil" sortable="true"><div align="center">${row_rowNum}</div></display:column>
                                    <display:column title="Cara Bayaran" class="tunai">
                                        <s:select name="senaraiCaraBayaran[${row_rowNum - 1}].kodCaraBayaran.kod"
                                                  id="senaraiCaraBayaran${row_rowNum - 1}" onchange="javaScript:change(${row_rowNum -1})">
                                            <s:option value="0" label="Pilih ..." />
                                            <s:options-collection collection="${listUtil.senaraiKodCaraBayaran}"  label="nama" value="kod" sort="nama"/>
                                        </s:select>
                                    </display:column>

                                    <display:column title="Bank / Agensi Pembayaran" >
                                        <s:select name="senaraiCaraBayaran[${row_rowNum - 1}].bank.kod" id="bank${row_rowNum - 1}">
                                            <s:option label="Pilih..." value="0" />
                                            <s:options-collection collection="${listUtil.senaraiKodBank}"  label="nama" value="kod" />
                                        </s:select>
                                    </display:column>

                                    <display:column title="Cawangan" >
                                        <s:text name="senaraiCaraBayaran[${row_rowNum - 1}].bankCawangan" id="caw${row_rowNum - 1}" size="20" />
                                    </display:column>

                                    <display:column title="No. Rujukan" >
                                        <em id="a${row_rowNum - 1}">*</em><s:text name="senaraiCaraBayaran[${row_rowNum - 1}].noRujukan" id="rujukan${row_rowNum - 1}" size="20" />
                                    </display:column>

                                    <display:column title="Tarikh">
                                        <s:text name="tarikhCek[${row_rowNum-1}]" id="trkh${row_rowNum - 1}" size="20" readonly="true" maxlength="10" class="datepicker"/>
                                    </display:column>

                                    <display:column title="Amaun (RM)">
                                        <s:text name="senaraiCaraBayaran[${row_rowNum - 1}].amaun" size="12" class="number"
                                                onblur="javascript:updateTotal(this,${row_rowNum - 1});" id="amaun${row_rowNum - 1}"/>
                                    </display:column>
                                    <display:footer>
                                <tr>
                                    <td colspan="6"><div align="right"><b>Jumlah (RM):</b></div></td>
                                    <td><input name="jumCaraBayar" value="0.00" id="jumCaraBayar" size="12" class="number" readonly="true"/></td>
                                </tr>
                            </display:footer>
                        </display:table >
                        </td>
                        </tr>
                    </table>
                    <%--<s:submit name="addCaraBayar" value="Tambah" class="btn"/>--%>
                </div>
                <br>
            </fieldset>
        </div>

        <br>


        <table border="0" bgcolor="green" width="100%">
            <tr>
                <td align="right">

                    <s:submit name="savePermohonan" value="Bayar" class="btn" />
                    <s:submit name="selectList" value="Kembali" class="btn" disabled="${actionBean.btn}"/>
                </td></tr> </table>

    </s:form>
    <script language="javascript" >
        var senaraiCaraBayaran0 = document.getElementById('senaraiCaraBayaran0');
        for (var i = 0; i < senaraiCaraBayaran0.length; i++){
            if (senaraiCaraBayaran0.options[i].value == 'T'){
                senaraiCaraBayaran0.selectedIndex = i;
                $("#hapus0").hide();
                $("#hapus10").hide();
                $("#hapus20").hide();
                break;
            }
        }
    </script>
</div>