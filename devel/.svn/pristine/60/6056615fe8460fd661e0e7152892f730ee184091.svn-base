<%-- 
    Document   : agihTugasan
    Created on : 01 Oktober 2009, 12:34:43 PM
    Author     : khairil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">

    function doAgih(e, f) {
        var i = $('#id_pguna').val();
        var stageId = $('#stageId').val();
    <%--var kodUrusan = $('#kodUrusan').val();--%>
            if(i == ''){
                alert('Sila pilih pengguna terlebih dahulu.');
                $('#id_pguna').focus();
                return false;
            }

            var nota = $('#permohonanNota').val();
            if(nota == 'false'){
                alert('Sila isi maklumat nota/kertas minit terlebih dahulu.');
                return false;
            }


            if(confirm('Adakah anda pasti? Sila semak dokumen terlebih dahulu jika belum semak.')) {
                $.blockUI({
                    message: $('#displayBox'),
                    css: {
                        top:  ($(window).height() - 50) /2 + 'px',
                        left: ($(window).width() - 50) /2 + 'px',
                        width: '50px'
                    }
                });
                var q = $(f).formSerialize();
                f.action = f.action + '?' + e + '&' + q;
                f.submit();
            }
        }


        function doQueryTask(val) {
            $('#tugasan').html('');
            $('#loading-img').show();
            var nama = $('#id_pguna option:selected').text();

            var url = '${pageContext.request.contextPath}/agihTugasan?query&idPengguna=' + val;
            $.get(url,
            function(data){
                $('#loading-img').hide();
                $('#tugasan').html( nama + ' mempunyai ' + data + ' tugasan.');
            }
        );
        }
    
</script>
<s:useActionBean beanclass="etanah.view.penguatkuasaan.AgihTugasanActionBean" var="penggunaperanan"/>
<s:form beanclass="etanah.view.penguatkuasaan.AgihTugasanActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <s:hidden name="statusNotaExist" id="permohonanNota"/>
            <s:hidden name="stageIDAgihTugas" id="stageId"/>
            <legend>
                Maklumat Fail
            </legend>
            <p>
                <label for="ID Berkaitan"> ID Aduan : </label>
                ${actionBean.mohon.idPermohonan}&nbsp;
            </p>

            <p><label for="Urusan"> Urusan : </label>
                ${actionBean.mohon.kodUrusan.nama}&nbsp;
            </p>
        </fieldset>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Agihan Tugas
            </legend>
            <%--agih tugasan melaka--%>
            <c:if test = "${actionBean.kodNegeri eq '04'}">
                <c:if test="${actionBean.mohon.kodUrusan.kod ne '49' && actionBean.mohon.kodUrusan.kod ne '422' && actionBean.mohon.kodUrusan.kod ne '423' && actionBean.mohon.kodUrusan.kod ne '424' && actionBean.mohon.kodUrusan.kod ne '429' && actionBean.mohon.kodUrusan.kod ne '425' && actionBean.mohon.kodUrusan.kod ne '127' && actionBean.mohon.kodUrusan.kod ne '426' && actionBean.mohon.kodUrusan.kod ne '425A'}">
                    <p><label>Hantar Kepada :</label>
                        <s:select name="pengguna.idPengguna" style="width:300px;" id="id_pguna" onchange="doQueryTask(this.value);">
                            <s:option value="">-- Sila Pilih --</s:option>
                            <c:forEach items="${penggunaperanan.listPp}" var="line">
                                <s:option value="${line.idPengguna}">${line.nama} : ${line.perananUtama.kod} (${line.kekananan})</s:option>
                            </c:forEach>
                        </s:select>
                        <img alt="Gambar" src="${pageContext.request.contextPath}/pub/images/loading_img.gif" style="display:none" id="loading-img"/>
                        <em id="tugasan"/>
                    </p>
                </c:if>
                <c:if test="${actionBean.mohon.kodUrusan.kod eq '425' || actionBean.mohon.kodUrusan.kod eq '425A'}">
                    <c:if test="${actionBean.stageIDAgihTugas eq 'agih_tugasan3'}">
                        <p><label>Hantar Kepada :</label>
                            <s:select name="pengguna.idPengguna" style="width:300px;" id="id_pguna" onchange="doQueryTask(this.value);">
                                <s:option value="">-- Sila Pilih --</s:option>
                                <c:forEach items="${penggunaperanan.listPt}" var="line">
                                    <s:option value="${line.idPengguna}">${line.nama} : ${line.perananUtama.kod} (${line.kekananan})</s:option>
                                </c:forEach>
                            </s:select>
                            <img alt="Gambar" src="${pageContext.request.contextPath}/pub/images/loading_img.gif" style="display:none" id="loading-img"/>
                            <em id="tugasan"/>
                        </p>
                    </c:if>
                    <c:if test="${actionBean.stageIDAgihTugas eq 'agih_tugasan2' || actionBean.stageIDAgihTugas eq 'agih_tugasan1'}">
                        <p><label>Hantar Kepada :</label>
                            <s:select name="pengguna.idPengguna" style="width:300px;" id="id_pguna" onchange="doQueryTask(this.value);">
                                <s:option value="">-- Sila Pilih --</s:option>
                                <c:forEach items="${penggunaperanan.listPp}" var="line">
                                    <s:option value="${line.idPengguna}">${line.nama} : ${line.perananUtama.kod} (${line.kekananan})</s:option>
                                </c:forEach>
                            </s:select>
                            <img alt="Gambar" src="${pageContext.request.contextPath}/pub/images/loading_img.gif" style="display:none" id="loading-img"/>
                            <em id="tugasan"/>
                        </p>
                    </c:if>
                    <c:if test="${actionBean.stageIDAgihTugas eq 'keputusan_laporan1'}">
                        <p><label>Hantar Kepada :</label>
                            <s:select name="pengguna.idPengguna" style="width:300px;" id="id_pguna" onchange="doQueryTask(this.value);">
                                <s:option value="">-- Sila Pilih --</s:option>
                                <c:forEach items="${penggunaperanan.listPptk}" var="line">
                                    <s:option value="${line.idPengguna}">${line.nama} : ${line.perananUtama.kod} (${line.kekananan})</s:option>
                                </c:forEach>
                            </s:select>
                            <img alt="Gambar" src="${pageContext.request.contextPath}/pub/images/loading_img.gif" style="display:none" id="loading-img"/>
                            <em id="tugasan"/>
                        </p>
                    </c:if>
                    <c:if test="${actionBean.stageIDAgihTugas ne 'agih_tugasan2' &&  actionBean.stageIDAgihTugas ne 'agih_tugasan1' &&  actionBean.stageIDAgihTugas ne 'agih_tugasan3' &&  actionBean.stageIDAgihTugas ne 'keputusan_laporan1'}">
                        <p><label>Hantar Kepada :</label>
                            <s:select name="pengguna.idPengguna" style="width:300px;" id="id_pguna" onchange="doQueryTask(this.value);">
                                <s:option value="">-- Sila Pilih --</s:option>
                                <c:forEach items="${penggunaperanan.listIo}" var="line">
                                    <s:option value="${line.idPengguna}">${line.nama} : ${line.perananUtama.kod} (${line.kekananan})</s:option>
                                </c:forEach>
                            </s:select>
                            <img alt="Gambar" src="${pageContext.request.contextPath}/pub/images/loading_img.gif" style="display:none" id="loading-img"/>
                            <em id="tugasan"/>
                        </p>
                    </c:if>
                </c:if>
                <c:if test="${actionBean.mohon.kodUrusan.kod eq '426' && actionBean.stageIDAgihTugas eq 'agih_tugasan_jualan'}">
                    <p><label>Hantar Kepada :</label>
                        <s:select name="pengguna.idPengguna" style="width:300px;" id="id_pguna" onchange="doQueryTask(this.value);">
                            <s:option value="">-- Sila Pilih --</s:option>
                            <c:forEach items="${penggunaperanan.listPt}" var="line">
                                <s:option value="${line.idPengguna}">${line.nama} : ${line.perananUtama.kod} (${line.kekananan})</s:option>
                            </c:forEach>
                        </s:select>
                        <img alt="Gambar" src="${pageContext.request.contextPath}/pub/images/loading_img.gif" style="display:none" id="loading-img"/>
                        <em id="tugasan"/>
                    </p>
                </c:if>
                <c:if test="${actionBean.mohon.kodUrusan.kod eq '426' && actionBean.stageIDAgihTugas eq 'agih_tugasan'}">
                    <p><label>Hantar Kepada :</label>
                        <s:select name="pengguna.idPengguna" style="width:300px;" id="id_pguna" onchange="doQueryTask(this.value);">
                            <s:option value="">-- Sila Pilih --</s:option>
                            <c:forEach items="${penggunaperanan.listPp}" var="line">
                                <s:option value="${line.idPengguna}">${line.nama} : ${line.perananUtama.kod} (${line.kekananan})</s:option>
                            </c:forEach>
                        </s:select>
                        <img alt="Gambar" src="${pageContext.request.contextPath}/pub/images/loading_img.gif" style="display:none" id="loading-img"/>
                        <em id="tugasan"/>
                    </p>
                </c:if>

                <c:if test="${actionBean.mohon.kodUrusan.kod eq '422' || actionBean.mohon.kodUrusan.kod eq '423' || actionBean.mohon.kodUrusan.kod eq '424' || actionBean.mohon.kodUrusan.kod eq '429'}">
                    <p><label>Hantar Kepada :</label>
                        <s:select name="pengguna.idPengguna" style="width:300px;" id="id_pguna" onchange="doQueryTask(this.value);">
                            <s:option value="">-- Sila Pilih --</s:option>
                            <c:forEach items="${penggunaperanan.listPptd}" var="line">
                                <s:option value="${line.idPengguna}">${line.nama} : ${line.perananUtama.kod} (${line.kekananan})</s:option>
                            </c:forEach>
                        </s:select>
                        <img alt="Gambar" src="${pageContext.request.contextPath}/pub/images/loading_img.gif" style="display:none" id="loading-img"/>
                        <em id="tugasan"/>
                    </p>
                </c:if>

                <c:if test="${actionBean.mohon.kodUrusan.kod eq '127' && actionBean.stageIDAgihTugas eq 'agih_charting'}">
                    <p><label>Hantar Kepada :</label>
                        <s:select name="pengguna.idPengguna" style="width:300px;" id="id_pguna" onchange="doQueryTask(this.value);">
                            <s:option value="">-- Sila Pilih --</s:option>
                            <c:forEach items="${penggunaperanan.listPp1}" var="line">
                                <s:option value="${line.idPengguna}">${line.nama} : ${line.perananUtama.kod} (${line.kekananan})</s:option>
                            </c:forEach>
                        </s:select>
                        <img alt="Gambar" src="${pageContext.request.contextPath}/pub/images/loading_img.gif" style="display:none" id="loading-img"/>
                        <em id="tugasan"/>
                    </p>
                </c:if>

                <c:if test="${actionBean.mohon.kodUrusan.kod eq '127' && actionBean.stageIDAgihTugas eq 'agih_tugasan'}">
                    <p><label>Hantar Kepada :</label>
                        <s:select name="pengguna.idPengguna" style="width:300px;" id="id_pguna" onchange="doQueryTask(this.value);">
                            <s:option value="">-- Sila Pilih --</s:option>
                            <c:forEach items="${penggunaperanan.listPp}" var="line">
                                <s:option value="${line.idPengguna}">${line.nama} : ${line.perananUtama.kod} (${line.kekananan})</s:option>
                            </c:forEach>
                        </s:select>
                        <img alt="Gambar" src="${pageContext.request.contextPath}/pub/images/loading_img.gif" style="display:none" id="loading-img"/>
                        <em id="tugasan"/>
                    </p>
                </c:if>
                <c:if test="${actionBean.mohon.kodUrusan.kod eq '49'}">
                    <c:if test="${actionBean.stageIDAgihTugas eq 'g_charting_semak'}">
                        <p><label>Hantar Kepada :</label>
                            <s:select name="pengguna.idPengguna" style="width:300px;" id="id_pguna" onchange="doQueryTask(this.value);">
                                <s:option value="">-- Sila Pilih --</s:option>
                                <c:forEach items="${penggunaperanan.listPp}" var="line">
                                    <s:option value="${line.idPengguna}">${line.nama} : ${line.perananUtama.kod} (${line.kekananan})</s:option>
                                </c:forEach>
                                <%--<s:options-collection collection="${penggunaperanan.listPp}" value="idPengguna" label="nama" />--%>
                            </s:select> <img alt="Gambar" src="${pageContext.request.contextPath}/pub/images/loading_img.gif" style="display:none" id="loading-img"/>
                            <em id="tugasan"/>
                        </p>
                    </c:if>
                    <c:if test="${actionBean.stageIDAgihTugas eq 'agih_tugasan1' || actionBean.stageIDAgihTugas eq 'agih_tugasan2'}">
                        <p><label>Hantar Kepada :</label>
                            <s:select name="pengguna.idPengguna" style="width:300px;" id="id_pguna" onchange="doQueryTask(this.value);">
                                <s:option value="">-- Sila Pilih --</s:option>
                                <c:forEach items="${penggunaperanan.listPp1}" var="line">
                                    <s:option value="${line.idPengguna}">${line.nama} : ${line.perananUtama.kod} (${line.kekananan})</s:option>
                                </c:forEach>
                                <%--<s:options-collection collection="${penggunaperanan.listPp1}" value="idPengguna" label="nama" />--%>
                            </s:select> <img alt="Gambar" src="${pageContext.request.contextPath}/pub/images/loading_img.gif" style="display:none" id="loading-img"/>
                            <em id="tugasan"/>
                        </p>
                    </c:if>
                </c:if>
            </c:if>
            <%--agih tugasan negeri--%>
            <c:if test = "${actionBean.kodNegeri eq '05'}">
                <c:choose>
                    <c:when test="${actionBean.mohon.kodUrusan.kod eq '422' ||  actionBean.mohon.kodUrusan.kod eq '423' || actionBean.mohon.kodUrusan.kod eq '424' || actionBean.mohon.kodUrusan.kod eq '427' || actionBean.mohon.kodUrusan.kod eq '428'}">
                        <c:if test = "${actionBean.stageIDAgihTugas eq 'maklum_arahan' || actionBean.stageIDAgihTugas eq 'terima_arahan_pemantauan'}">
                            <p><label>Hantar Kepada :</label>
                                <s:select name="pengguna.idPengguna" style="width:300px;" id="id_pguna" onchange="doQueryTask(this.value);">
                                    <s:option value="">-- Sila Pilih --</s:option>
                                    <c:forEach items="${penggunaperanan.listPp}" var="line">
                                        <s:option value="${line.idPengguna}">${line.nama} : ${line.perananUtama.kod} (${line.kekananan})</s:option>
                                    </c:forEach>
                                </s:select>
                                <img alt="Gambar" src="${pageContext.request.contextPath}/pub/images/loading_img.gif" style="display:none" id="loading-img"/>
                                <em id="tugasan"/>
                            </p>
                        </c:if>
                        <c:if test = "${actionBean.stageIDAgihTugas eq 'maklum_aduan'}">
                            <p><label>Hantar Kepada :</label>
                                <s:select name="pengguna.idPengguna" style="width:300px;" id="id_pguna" onchange="doQueryTask(this.value);">
                                    <s:option value="">-- Sila Pilih --</s:option>
                                    <c:forEach items="${penggunaperanan.listPptk}" var="line">
                                        <s:option value="${line.idPengguna}">${line.nama} : ${line.perananUtama.kod}</s:option>
                                    </c:forEach>
                                </s:select>
                                <img alt="Gambar" src="${pageContext.request.contextPath}/pub/images/loading_img.gif" style="display:none" id="loading-img"/>
                                <em id="tugasan"/>
                            </p>
                        </c:if>
                        <c:if test = "${actionBean.stageIDAgihTugas eq 'keputusan_tindakan' || actionBean.stageIDAgihTugas eq 'keputusan1' || actionBean.stageIDAgihTugas eq 'maklum_tidak_bayar_kompaun' || actionBean.stageIDAgihTugas eq 'keputusan2'}">
                            <p><label>Hantar Kepada :</label>
                                <s:select name="pengguna.idPengguna" style="width:300px;" id="id_pguna" onchange="doQueryTask(this.value);">
                                    <s:option value="">-- Sila Pilih --</s:option>
                                    <c:forEach items="${penggunaperanan.listIo}" var="line">
                                        <s:option value="${line.idPengguna}">${line.nama} : ${line.perananUtama.kod}</s:option>
                                    </c:forEach>
                                </s:select>
                                <img alt="Gambar" src="${pageContext.request.contextPath}/pub/images/loading_img.gif" style="display:none" id="loading-img"/>
                                <em id="tugasan"/>
                            </p>
                        </c:if>
                    </c:when>
                    <c:when test="${actionBean.mohon.kodUrusan.kod eq '429'}">
                        <c:if test = "${actionBean.stageIDAgihTugas eq 'maklum_aduan'}">
                            <c:choose>
                                <c:when test="${actionBean.mohon.cawangan.kod eq '10'}">
                                    <p><label>Hantar Kepada :</label>
                                        <s:select name="pengguna.idPengguna" style="width:300px;" id="id_pguna" onchange="doQueryTask(this.value);">
                                            <s:option value="">-- Sila Pilih --</s:option>
                                            <c:forEach items="${penggunaperanan.listPptg}" var="line">
                                                <s:option value="${line.idPengguna}">${line.nama}</s:option>
                                            </c:forEach>
                                        </s:select>
                                        <img alt="Gambar" src="${pageContext.request.contextPath}/pub/images/loading_img.gif" style="display:none" id="loading-img"/>
                                        <em id="tugasan"/>
                                    </p>
                                </c:when>
                                <c:otherwise>
                                    <p><label>Hantar Kepada :</label>
                                        <s:select name="pengguna.idPengguna" style="width:300px;" id="id_pguna" onchange="doQueryTask(this.value);">
                                            <s:option value="">-- Sila Pilih --</s:option>
                                            <c:forEach items="${penggunaperanan.listPptd}" var="line">
                                                <s:option value="${line.idPengguna}">${line.nama}</s:option>
                                            </c:forEach>
                                        </s:select>
                                        <img alt="Gambar" src="${pageContext.request.contextPath}/pub/images/loading_img.gif" style="display:none" id="loading-img"/>
                                        <em id="tugasan"/>
                                    </p>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                    </c:when>
                    <c:when test="${actionBean.mohon.kodUrusan.kod eq '351' || actionBean.mohon.kodUrusan.kod eq '352'}">
                        <p><label>Hantar Kepada :</label>
                            <s:select name="pengguna.idPengguna" style="width:300px;" id="id_pguna" onchange="doQueryTask(this.value);">
                                <s:option value="">-- Sila Pilih --</s:option>
                                <c:forEach items="${penggunaperanan.listPp}" var="line">
                                    <%--<s:option value="${line.idPengguna}">${line.nama} : ${line.perananUtama.kod} (${line.kekananan})</s:option>--%>
                                    <s:option value="${line.idPengguna}">${line.nama}</s:option>
                                </c:forEach>
                                <%--<s:options-collection collection="${penggunaperanan.listPp}" value="idPengguna" label="nama" />--%>
                            </s:select> <img alt="Gambar" src="${pageContext.request.contextPath}/pub/images/loading_img.gif" style="display:none" id="loading-img"/>
                            <em id="tugasan"/>
                        </p>
                    </c:when>
                    <c:when test="${actionBean.mohon.kodUrusan.kod eq '425' || actionBean.mohon.kodUrusan.kod eq '425A'}">
                        <c:if test = "${actionBean.stageIDAgihTugas eq 'agih_tugasan1' || actionBean.stageIDAgihTugas eq 'agih_tugasan2' || actionBean.stageIDAgihTugas eq 'agih_tugasan3'}">
                            <p><label>Hantar Kepada :</label>
                                <s:select name="pengguna.idPengguna" id="id_pguna" onchange="doQueryTask(this.value);">
                                    <s:option value="">-- Sila Pilih --</s:option>
                                    <c:forEach items="${penggunaperanan.listPp}" var="line">
                                        <%--<s:option value="${line.idPengguna}">${line.nama} : ${line.perananUtama.kod} (${line.kekananan})</s:option>--%>
                                        <s:option value="${line.idPengguna}">${line.nama}</s:option>
                                    </c:forEach>
                                    <%--<s:options-collection collection="${penggunaperanan.listPp}" value="idPengguna" label="nama" />--%>
                                </s:select> <img alt="Gambar" src="${pageContext.request.contextPath}/pub/images/loading_img.gif" style="display:none" id="loading-img"/>
                                <em id="tugasan"/>
                            </p>
                        </c:if>
                        <c:if test="${actionBean.stageIDAgihTugas eq 'kpsn2' 
                                      || actionBean.stageIDAgihTugas eq 'kpsn3'
                                      || actionBean.stageIDAgihTugas eq 'kemaskini_inventori'
                                      || actionBean.stageIDAgihTugas eq 'tindakan_simpan_barang'
                                      || actionBean.stageIDAgihTugas eq 'kpsn4'
                                      || actionBean.stageIDAgihTugas eq 'maklum_bayaran_kompaun'
                                      || actionBean.stageIDAgihTugas eq 'maklum_tidak_bayar_kompaun'
                                      || actionBean.stageIDAgihTugas eq 'maklum_rayuan_kompaun'
                                      || actionBean.stageIDAgihTugas eq 'peraku_surat_lepas_brg'}"> <!-- IO -->
                              <p><label>Hantar Kepada :</label>
                                  <s:select name="pengguna.idPengguna" style="width:300px;" id="id_pguna" onchange="doQueryTask(this.value);">
                                      <s:option value="">-- Sila Pilih --</s:option>
                                      <c:forEach items="${penggunaperanan.listIo}" var="line">
                                          <s:option value="${line.idPengguna}">${line.nama}</s:option>
                                      </c:forEach>
                                  </s:select> <img alt="Gambar" src="${pageContext.request.contextPath}/pub/images/loading_img.gif" style="display:none" id="loading-img"/>
                                  <em id="tugasan"/>
                              </p>
                        </c:if>
                        <c:if test="${actionBean.stageIDAgihTugas eq 'arah_operasi1' || actionBean.stageIDAgihTugas eq 'arah_simpan_barang' || actionBean.stageIDAgihTugas eq 'terima_jualan'}"> <!-- RO -->
                            <p><label>Hantar Kepada :</label>
                                <s:select name="pengguna.idPengguna" style="width:300px;" id="id_pguna" onchange="doQueryTask(this.value);">
                                    <s:option value="">-- Sila Pilih --</s:option>
                                    <c:forEach items="${penggunaperanan.listRo}" var="line">
                                        <s:option value="${line.idPengguna}">${line.nama}</s:option>
                                    </c:forEach>
                                </s:select> <img alt="Gambar" src="${pageContext.request.contextPath}/pub/images/loading_img.gif" style="display:none" id="loading-img"/>
                                <em id="tugasan"/>
                            </p>
                        </c:if>
                    </c:when>
                    <c:when test="actionBean.mohon.kodUrusan.kod eq '426'">

                    </c:when>
                    <c:otherwise>
                        <p><label>Hantar Kepada :</label>
                            <s:select name="pengguna.idPengguna" style="width:300px;" id="id_pguna" onchange="doQueryTask(this.value);">
                                <s:option value="">-- Sila Pilih --</s:option>
                                <c:forEach items="${penggunaperanan.listPp}" var="line">
                                    <s:option value="${line.idPengguna}">${line.nama} : ${line.perananUtama.kod} (${line.kekananan})</s:option>
                                </c:forEach>
                                <%--<s:options-collection collection="${penggunaperanan.listPp}" value="idPengguna" label="nama" />--%>
                            </s:select> <img alt="Gambar" src="${pageContext.request.contextPath}/pub/images/loading_img.gif" style="display:none" id="loading-img"/>
                            <em id="tugasan"/>
                        </p>
                    </c:otherwise>
                </c:choose>
            </c:if>
            <p>
                <label>&nbsp;</label>
                <s:button name="agihPT" value="Agih" class="btn" onclick="doAgih(this.name, this.form);"/>
            </p>
        </fieldset>
    </div>
</s:form>