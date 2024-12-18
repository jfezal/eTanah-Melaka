<%--
    Document   : nota_daftar_luascukaisyarat
    Created on : Dec 31, 2009, 2:06:56 PM
    Author     : mohd.fairul
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript">



    function popup(f,h,p){
        var url = '${pageContext.request.contextPath}/daftar/nota/nota_daftar?popup&idH='+h+'&id='+p;
    <%--     alert(p);--%>
            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=290");


        }



</script>
<s:form beanclass="etanah.view.stripes.nota.NotaDaftarActionBean">

    <s:messages />
    <s:errors />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Nota
            </legend>
            <p style="color:red">
                *Isi Yang Berkenaan Sahaja.
            </p>

            <p>
                <label for="noFail">Nombor Fail / ID Permohonan :</label>
                <s:text name="permohonanRujLuar.noFail"></s:text>
            </p>
            <p>
                <label>Nombor Kertas:</label>
                <s:select name="permohonanRujLuar.namaSidang" id="kodMesy" style="width:6%;" >
                    <s:option value="" >Sila Pilih</s:option>
                    <s:option value="PTD">PTD</s:option>
                    <s:option value="MMK">MMK</s:option>
                    <s:option value="PTG">PTG</s:option>
                    <s:option value="MB">MB</s:option>
                </s:select>

                <s:text name="permohonanRujLuar.noSidang" style="width:6%;"></s:text>

            </p>
            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'N6A'
                          or actionBean.permohonan.kodUrusan.kod ne 'N7A'
                          or actionBean.permohonan.kodUrusan.kod ne 'N8A'}">
                  <p>
                      <label>Tarikh Mesyuarat :</label>
                      <s:text name="tarikhSidang" class="datepicker"/>
                  </p>
            </c:if>
              <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'N6A'
                          or actionBean.permohonan.kodUrusan.kod eq 'N7A'
                          or actionBean.permohonan.kodUrusan.kod eq 'N8A'}">
                  <p>
                      <label>Tarikh Disampai :</label>
                      <s:text name="tarikhSidang" class="datepicker"/>
                  </p>
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'HTBKR'
                        or actionBean.permohonan.kodUrusan.kod eq 'HTB'}">
            <p>
                <label>Nombor Warta :</label>
                <s:text name="permohonanRujLuar.noRujukan" ></s:text>
            </p>
            <p>
                <label>Tarikh Warta :</label>
                <s:text name="tarikhRujukan" id="datepicker" class="datepicker"/>
            </p>
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'HTBKR'
                            or actionBean.permohonan.kodUrusan.kod eq 'HTB'}">
            <p>
                <label>No. Buku :</label>
                <s:text name="permohonanRujLuar.noRujukan" ></s:text>
            </p>
            <p>
                <label>Tarikh Buku :</label>
                <s:text name="tarikhRujukan" id="datepicker" class="datepicker"/>
            </p>
           </c:if>



            <br/>
            <table style="margin-left: auto; margin-right: auto;">
                <tr>
                    <td>&nbsp;</td>
                    <td><div >

                            <s:button class="btn" onclick="doSubmit(this.form, this.name, 'page_div');" name="simpanMaklumatluascukaisyarat" value="Simpan"/>


                        </div>
                    </td>
                </tr>
            </table>
            <br/>
        </fieldset>


        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSSKM'
                      or actionBean.permohonan.kodUrusan.kod eq 'PSTSM'
                      or actionBean.permohonan.kodUrusan.kod eq 'SSKPM'}">
              <fieldset class="aras1">
                  <legend>
                      Maklumat Asal
                  </legend>

                  <br/>

                  <div class="content" align="center" >
                      <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="10" cellpadding="0" cellspacing="0" id="line">
                          <display:column title="No." sortable="true">${line_rowNum}</display:column>
                          <display:column property="hakmilik.idHakmilik" title="ID Hakmilik"/>
                          <display:column property="hakmilik.kategoriTanah.nama" title="Kategori Tanah (Lama)"></display:column>
                          <display:column property="hakmilik.syaratNyata.kod" title="Kod SyaratNyata"></display:column>
                          <display:column property="hakmilik.sekatanKepentingan.kod" title="Kod Sekatan"/>
                          <display:column property="hakmilik.luas" title="Luas"/>
                          <display:column property="hakmilik.kodUnitLuas.kod" title="Unit Luas" />
                          <display:column property="hakmilik.cukai" title="Cukai (RM)"/>

                      </display:table>

                  </div>
                  <br/>


              </fieldset>

              <fieldset class="aras1">
                  <legend>
                      Kemasukan Nilai Baru
                  </legend>
                  <p style="color:red">
                      *Klik Icon (<img alt='Klik Untuk Kemsakini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem' />) untuk kemaskini data.
                  </p>
                  <div class="content" align="center" >
                      <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="10" cellpadding="0" cellspacing="0" id="line">
                          <display:column title="No." sortable="true">${line_rowNum}</display:column>
                          <display:column property="hakmilik.idHakmilik" title="ID Hakmilik"/>
                          <display:column property="kategoriTanahBaru.nama" title="Kategori Tanah"></display:column>
                          <display:column property="syaratNyataBaru.kod" title="Kod SyaratNyata"></display:column>
                          <display:column property="sekatanKepentinganBaru.kod" title="Kod Sekatan"/>
                          <display:column property="luasTerlibat" title="Luas Terlibat"/>
                          <display:column property="kodUnitLuas.kod" title="Unit Luas" />
                          <display:column property="cukaiBaru" title="Cukai (RM)"/>
                          <display:column title="Kemaskini">
                              <div align="center">
                                  <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                       id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('this.form','${line.hakmilik.idHakmilik}','${line.id}')"/>
                              </div>
                          </display:column>
                      </display:table>


                  </div>
                  <br/>


              </fieldset>
        </c:if>
        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'ABTKB'
                      or actionBean.permohonan.kodUrusan.kod eq 'PSM'
                      or actionBean.permohonan.kodUrusan.kod eq 'SBSTM'
                      or actionBean.permohonan.kodUrusan.kod eq 'ABTBH'
              }">
            <%--luas dan cukai--%>
            <fieldset class="aras1">
                <legend>
                    Maklumat Asal
                </legend>

                <br/>

                <div class="content" align="center" >
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="10" cellpadding="0" cellspacing="0" id="line">
                        <display:column title="No." sortable="true">${line_rowNum}</display:column>
                        <display:column property="hakmilik.idHakmilik" title="ID Hakmilik"/>
                        <display:column property="hakmilik.luas" title="Luas"/>
                        <display:column property="hakmilik.kodUnitLuas.kod" title="Unit Luas" />
                        <display:column property="hakmilik.cukai" title="Cukai (RM)"/>
                    </display:table>
                </div>
                <br/>
            </fieldset>
            <fieldset class="aras1">
                <legend>
                    Kemasukan Nilai Baru
                </legend>
                <p style="color:red">
                    *Klik Icon (<img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem' />) untuk kemaskini data.
                </p>
                <div class="content" align="center" >
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="10" cellpadding="0" cellspacing="0" id="line">
                        <display:column title="No." sortable="true">${line_rowNum}</display:column>
                        <display:column property="hakmilik.idHakmilik" title="ID Hakmilik"/>
                        <display:column property="luasTerlibat" title="Luas Terlibat"/>
                        <display:column property="kodUnitLuas.kod" title="Unit Luas" />
                        <display:column property="cukaiBaru" title="Cukai (RM)"/>
                        <display:column title="Kemaskini">
                            <div align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('this.form','${line.hakmilik.idHakmilik}','${line.id}')"/>
                            </div>
                        </display:column>
                    </display:table>
                </div>
                <br/>
            </fieldset>
        </c:if>
        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PSKSM'
                      or actionBean.permohonan.kodUrusan.kod eq 'TSM'}">
            <%--SYARAT CUKAI--%>
            <fieldset class="aras1">
                <legend>
                    Maklumat Asal
                </legend>
                <br/>
                <div class="content" align="center" >
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="10" cellpadding="0" cellspacing="0" id="line">
                        <display:column title="No." sortable="true">${line_rowNum}</display:column>
                        <display:column property="hakmilik.idHakmilik" title="ID Hakmilik"/>
                        <display:column property="hakmilik.syaratNyata.kod" title="Kod SyaratNyata"></display:column>
                        <display:column property="hakmilik.sekatanKepentingan.kod" title="Kod Sekatan"/>
                        <display:column property="hakmilik.cukai" title="Cukai (RM)"/>
                    </display:table>

                </div>
                <br/>
            </fieldset>

            <fieldset class="aras1">
                <legend>
                    Kemasukan Nilai Baru
                </legend>
                <p style="color:red">
                    *Klik Icon (<img alt='Klik Untuk Kemsakini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem' />) untuk kemaskini data.
                </p>
                <div class="content" align="center" >
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="10" cellpadding="0" cellspacing="0" id="line">
                        <display:column title="No." sortable="true">${line_rowNum}</display:column>
                        <display:column property="hakmilik.idHakmilik" title="ID Hakmilik"/>
                        <display:column property="syaratNyataBaru.kod" title="Kod SyaratNyata"></display:column>
                        <display:column property="sekatanKepentinganBaru.kod" title="Kod Sekatan"/>
                        <display:column property="cukaiBaru" title="Cukai (RM)"/>
                        <display:column title="Kemaskini">
                            <div align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('this.form','${line.hakmilik.idHakmilik}','${line.id}')"/>
                            </div>
                        </display:column>
                    </display:table>

                </div>
                <br/>
            </fieldset>
        </c:if>

        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'HLLA'
                      or actionBean.permohonan.kodUrusan.kod eq 'HLLS'
                      or actionBean.permohonan.kodUrusan.kod eq 'HLTE'
                      or actionBean.permohonan.kodUrusan.kod eq 'ABT-A'
                        or actionBean.permohonan.kodUrusan.kod eq 'ABT-D'}">
            <%--SYARAT CUKAI--%>
            <fieldset class="aras1">
                <legend>
                    Maklumat Asal
                </legend>
                <br/>
                <div class="content" align="center" >
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="10" cellpadding="0" cellspacing="0" id="line">
                        <display:column title="No." sortable="true">${line_rowNum}</display:column>
                        <display:column property="hakmilik.idHakmilik" title="ID Hakmilik"/>
                        <display:column property="hakmilik.luas" title="Luas Keseluruhan"/>
                        <display:column property="hakmilik.kodUnitLuas.kod" title="Unit Luas" />
                     </display:table>

                </div>
                <br/>
            </fieldset>

            <fieldset class="aras1">
                <legend>
                    Kemasukan Nilai Baru
                </legend>
                <p style="color:red">
                    *Klik Icon (<img alt='Klik Untuk Kemsakini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem' />) untuk kemaskini data.
                </p>
                <div class="content" align="center" >
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="10" cellpadding="0" cellspacing="0" id="line">
                        <display:column title="No." sortable="true">${line_rowNum}</display:column>
                        <display:column property="hakmilik.idHakmilik" title="ID Hakmilik"/>
                        <display:column property="luasTerlibat" title="Luas Terlibat"/>
                        <display:column property="kodUnitLuas.kod" title="Unit Luas" />
                        <display:column title="Kemaskini">
                            <div align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('this.form','${line.hakmilik.idHakmilik}','${line.id}')"/>
                            </div>
                        </display:column>
                    </display:table>


                </div>
                <br/>
            </fieldset>
        </c:if>

                             <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PCT'
                                            or actionBean.permohonan.kodUrusan.kod eq 'KCL'}">
            <%--SYARAT CUKAI--%>
            <%--<fieldset class="aras1">
                <legend>
                    Maklumat Asal
                </legend>
                <br/>
                <div class="content" align="center" >
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="10" cellpadding="0" cellspacing="0" id="line">
                        <display:column title="No." sortable="true">${line_rowNum}</display:column>
                        <display:column property="hakmilik.idHakmilik" title="ID Hakmilik"/>
                        <display:column property="hakmilik.cukai" title="Cukai (RM)"/>
                    </display:table>

                </div>
                <br/>
            </fieldset>--%>

            <fieldset class="aras1">
                <legend>
                    Nilai Pindaan Cukai
                </legend>
                <p style="color:red">
                    *Klik Icon (<img alt='Klik Untuk Kemsakini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem' />) untuk kemaskini data.
                </p>
                <div class="content" align="center" >
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="10" cellpadding="0" cellspacing="0" id="line">
                        <display:column title="No." sortable="true">${line_rowNum}</display:column>
                        <display:column property="hakmilik.idHakmilik" title="ID Hakmilik"/>
                        <display:column property="cukaiBaru" title="Cukai (RM)"/>
                       <%-- <display:column title="Kemaskini">
                            <div align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('this.form','${line.hakmilik.idHakmilik}','${line.id}')"/>
                            </div>
                        </display:column>--%>
                    </display:table>

                </div>
                <br/>
            </fieldset>
        </c:if>

    </div>
    <br>

</s:form>
