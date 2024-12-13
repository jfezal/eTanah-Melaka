<%--
    Document   : kemasukan_pihak_main
    Created on : Nov 5, 2010, 11:54:31 AM
    Author     : fikri
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

    function doEdit(d, j, d1){
        var idHakmilik = $('#hakmilik').val();
        window.open("${pageContext.request.contextPath}/daftar/pihak_kepentingan?showTukarForm&jenis_pihak="
            +j+"&id="+d + "&idPihak=" +d1 + '&idHakmilik=' + idHakmilik, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=900,scrollbars=yes");
    }
    
       function doEdit2(d, j, d1, idhakmilikPihak){
//        alert(idhakmilikPihak);
        var idHakmilik = $('#hakmilik').val();
        window.open("${pageContext.request.contextPath}/daftar/pihak_kepentingan?showTukarForm&jenis_pihak="
            +j+"&id="+d + "&idPihak=" +d1 + '&idHakmilik=' + idHakmilik + '&idhakmilikPihak=' + idhakmilikPihak, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=900,scrollbars=yes");
    }
    
    function doEditWaris(d, j, d1, idHp){
        var idHakmilik = $('#hakmilik').val();
        window.open("${pageContext.request.contextPath}/daftar/pihak_kepentingan?showTukarFormWaris&jenis_pihak="
            +j+"&id="+d + "&idPihak=" +d1 + '&idHakmilik=' + idHakmilik + '&idhp=' + idHp, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1500,height=900,scrollbars=yes");
    }

    function addNew(action){
        var idHakmilik = $('#hakmilik').val();
        var len = $('.nama').length;
        var param = '';
        doBlockUI();

        for(var i=1; i<=len; i++){

            var ckd = $('#chkbox'+i).is(":checked");
            if(ckd){
                param = param + '&uids=' + $('#chkbox'+i).val();
            }
        }

        if(param == ''){
            alert('Tiada pemohon.');
            doUnBlockUI();
            return;
        }

        var url = '${pageContext.request.contextPath}/daftar/pihak_kepentingan?' +action+param+'&jenisPihak=pemohon&idHakmilik=' + idHakmilik;
        if ($('#copyToAll').is(':checked')) {
            url = url + '&copyToAll=true';
        }

        $.ajax({
            type:"GET",
            url : url,
            dataType : 'html',
            error : function (xhr, ajaxOptions, thrownError){
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success : function(data){
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });
    }


    function remove2(action, cn, jenis, id){         
        var idHakmilik = $('#hakmilik').val();
        var len = $('.' + cn).length;
        var param = '';
        doBlockUI();

        for(var i=1; i<=len; i++){

            var ckd = $('#' + id +'_'+i).is(":checked");
            if(ckd){
                param = param + '&uids=' + $('#' + id +'_'+i).val();
            }
        }

        if(param == ''){
            alert('Tiada pemohon.');
            doUnBlockUI();
            return;
        }
        var url = '${pageContext.request.contextPath}/daftar/pihak_kepentingan?' +action+param + '&jenisPihak=' + jenis + '&idHakmilik=' + idHakmilik;

        $.ajax({
            type:"GET",
            url : url,
            dataType : 'html',
            error : function (xhr, ajaxOptions, thrownError){
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success : function(data){
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });
    }

    function reload (val) {
        doBlockUI();
        var url = '${pageContext.request.contextPath}/daftar/pihak_kepentingan?reload&idHakmilik=' + val;
        $.ajax({
            type:"GET",
            url : url,
            dataType : 'html',
            error : function (xhr, ajaxOptions, thrownError){
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success : function(data){
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });

    }

    function doOpen() {
        var idHakmilik = $('#hakmilik').val();
        var url = '${pageContext.request.contextPath}/daftar/pihak_kepentingan?showSearchForm&idHakmilik=' + idHakmilik;
        window.open(url,  "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800,scrollbars=yes");
    }

    function semakSyer(f){
        var q = $(f).formSerialize();
        $.post('${pageContext.request.contextPath}/daftar/pihak_kepentingan?semakSyer',q,
        function(data){
            if(data != ''){
                alert(data);
            }
        }, 'html');
    }

    function selectAll(a, clazz){
             var len = $('.' + clazz).length;            
             for (var i=1; i<=len; i++) {
                 var id = clazz + i;
                 var c = document.getElementById(id);
                 c.checked = a.checked;
             }
         }
         
          alert('Sila Pastikan Anda Mengisi Pada Tab PERSERAHAN TERLIBAT Terlebih Dahulu Sebelum Mengisi Maklumat Pada Tab Ini Sekiranya Ada.Gagal Berbuat Demikian TIDAK AKAN MENGUBAH MAKLUMAT TERKINI PADA PERSERAHAN');
</script>


<div class="subtitle displaytag">
    <s:messages/>
    <s:errors/>
    <s:form beanclass="etanah.view.daftar.PihakKepentinganAction" name="form1">
        <fieldset class="aras1">
            <legend>Senarai Hakmilik Terlibat</legend>
            <c:if test="${!empty moreThanOneHakmilik}">
                <p>
                    <label></label>&nbsp;
                    <font color="red" size="2">
                        <input type="checkbox" name="copyToAll" value="1" id="copyToAll"/>
                        <b><em>Sila klik jika sama untuk semua hakmilik.</em></b>
                    </font>
                </p>
            </c:if>
            <p>
                <label>Hakmilik :</label>
                <s:select name="idHakmilik" onchange="reload(this.value);" id="hakmilik">
                    <c:forEach items="${actionBean.senaraiHakmilikTerlibat}" var="item" varStatus="line">
                        <s:option value="${item.hakmilik.idHakmilik}" style="width:400px">
                            ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
                        </s:option>
                    </c:forEach>
                </s:select>
            </p>
            <br/>
        </fieldset>
        <br/>
        <fieldset class="aras1">
            <legend>
                <c:choose>
                    <c:when test="${bukan_tuan_tanah}">
                        Senarai Pihak Berkepentingan
                    </c:when>
                    <c:otherwise>
                        Senarai Pemilik
                    </c:otherwise>
                </c:choose>

            </legend>
            <p align="center">
                <c:choose>
                    <c:when test="${isBerangkai}">
                        <display:table style="width:70%;" class="tablecloth" name="${actionBean.senaraiPermohonanPihakBerangkai}"
                                       cellpadding="0" cellspacing="0" id="line">
                            <c:if test="${edit}">
                                <display:column title="<input type='checkbox' id='semua' name='semua' onclick='javascript:selectAll(this,\"chkbox\");'/>">
                                    <s:checkbox name="checkbox" id="chkbox${line_rowNum}" value="${line.pihak.idPihak}-${line.jenis.kod}-${line.syerPembilang}-${line.syerPenyebut}-${line.noRujukan}-${line.dalamanNilai2}" class="chkbox"/>
                                </display:column>
                            </c:if>
                            <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                            <%--<display:column property="pihak.nama" title="Nama" class="nama"/>
                            <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" /> --%>
                            <c:choose>
                                   <c:when test="${empty line.nama}">
                                       <display:column  title="Nama" property="pihak.nama" class="nama"/>
                                   </c:when>
                                   <c:otherwise>
                                       <display:column  title="Nama" property="nama" class="nama"/>
                                   </c:otherwise>
                               </c:choose>
                               <c:choose>
                                   <c:when test="${empty line.noPengenalan}">
                                       <display:column property="pihak.noPengenalan" title="No Pengenalan" style="width:15%;"/>
                                   </c:when>
                                   <c:otherwise>
                                       <display:column property="noPengenalan" title="No Pengenalan" style="width:15%;"/>
                                   </c:otherwise>
                               </c:choose>
                            
                            
                            <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                            <display:column title="Bahagian yang dimiliki" >
                            <div align="center">
                                ${line.syerPembilang}/${line.syerPenyebut}
                            </div>
                        </display:column>
                        <display:column property="jenis.nama" title="Jenis Pihak"/>
                    </display:table>
                </c:when>
                <c:otherwise>
                    <display:table style="width:70%;" class="tablecloth" name="${actionBean.senaraiKeempunyaan}"
                                   cellpadding="0" cellspacing="0" id="line">

                        <c:if test="${edit}">
                            <display:column title="<input type='checkbox' id='semua' name='semua' onclick='javascript:selectAll(this,\"chkbox\");'/>">
                                <s:checkbox name="checkbox" id="chkbox${line_rowNum}" value="${line.pihak.idPihak}-${line.jenis.kod}-${line.syerPembilang}-${line.syerPenyebut}--${line.idHakmilikPihakBerkepentingan}" class="chkbox"/>
                            </display:column>
                        </c:if>
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <%--<display:column  title="Nama" property="pihak.nama" class="nama"/>
                        <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />--%>
                        <c:choose>
                              <c:when test="${empty line.nama}">
                                  <display:column  title="Nama" property="pihak.nama" class="nama"/>
                              </c:when>
                              <c:otherwise>
                                  <display:column  title="Nama" property="nama" class="nama"/>
                              </c:otherwise>
                          </c:choose>
                          <c:choose>
                              <c:when test="${empty line.noPengenalan}">
                                  <display:column property="pihak.noPengenalan" title="No Pengenalan" style="width:15%;"/>
                              </c:when>
                              <c:otherwise>
                                  <display:column property="noPengenalan" title="No Pengenalan" style="width:15%;"/>
                              </c:otherwise>
                          </c:choose>
                        <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                        <display:column title="Bahagian yang dimiliki" >
                            <div align="center">
                                ${line.syerPembilang}/${line.syerPenyebut}
                            </div>
                        </display:column>
                        <display:column property="jenis.nama" title="Jenis Pihak"/>
                    </display:table>
                </c:otherwise>
            </c:choose>
            </p>
            <br/>
            <c:if test="${edit && ( fn:length(actionBean.senaraiKeempunyaan) > 0 || fn:length(actionBean.senaraiPermohonanPihakBerangkai) > 0 ) }">
                <p align="center">
                    <label></label>
                    <s:button name="add" onclick="addNew(this.name);" value="Simpan" class="btn"/>
                </p>
            </c:if>
        </fieldset>
        <br/>
        <fieldset class="aras1">
            <legend>Senarai Pihak Terlibat </legend>
            <font color="red">Sila tekan kemaskini untuk mengemaskini data.</font>
            <div class="content" align="center">

                <c:choose>
                    <c:when test="${fn:length(actionBean.senaraiPermohonanAtasPihak) > 0}">
                        <display:table style="width:50%;" class="tablecloth" name="${actionBean.senaraiPermohonanAtasPihak}"
                                       cellpadding="0" cellspacing="0" id="line1">
                            <c:if test="${edit}">
                                <display:column>
                                    <s:checkbox name="checkbox" id="rm_${line1_rowNum}" value="${line1.idAtasPihak}"/>
                                </display:column>
                            </c:if>
                             <display:column title="Bil">${line1_rowNum}</display:column>
                            <display:column title="Nama Pihak" property="pihakBerkepentingan.pihak.nama" class="remove"/>
                            <%--<display:column title="Alamat Surat Menyurat">
                                ${line1.pihakBerkepentingan.pihak.suratAlamat1}
                                <c:if test="${line1.pihakBerkepentingan.pihak.suratAlamat1 ne null && line1.pihakBerkepentingan.pihak.suratAlamat2 ne null}">
                                    , </c:if>
                                ${line1.pihakBerkepentingan.pihak.suratAlamat2}
                                <c:if test="${line1.pihakBerkepentingan.pihak.suratAlamat2 ne null && line1.pihakBerkepentingan.pihak.suratAlamat3 ne null}">
                                    , </c:if>
                                ${line1.pihakBerkepentingan.pihak.suratAlamat3}
                                <c:if test="${line1.pihakBerkepentingan.pihak.suratAlamat3 ne null && line1.pihakBerkepentingan.pihak.suratAlamat4 ne null}"> 
                                    ,
                                </c:if>
                                ${line1.pihakBerkepentingan.pihak.suratAlamat4}
                                <c:if test="${line1.pihakBerkepentingan.pihak.suratAlamat4 ne null && line1.pihakBerkepentingan.pihak.suratPoskod ne null}">
                                    ,</c:if>
                                ${line1.pihakBerkepentingan.pihak.suratPoskod}
                                <c:if test="${line1.pihakBerkepentingan.pihak.suratPoskod ne null && line1.pihakBerkepentingan.pihak.suratNegeri.kod ne null}">
                                    ,</c:if>
                                ${line1.pihakBerkepentingan.pihak.suratNegeri.nama}
                            </display:column>--%>
                            <c:if test="${edit}">
                                <display:column title="Kemaskini">
                                    <p align="center">
                                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="doEdit('${line1.idAtasPihak}', 'pemohon','${line1.pihakBerkepentingan.pihak.idPihak}');return false;"
                                             onmouseover="this.style.cursor='pointer';">
                                    </p>
                                </display:column>
                            </c:if>

                        </display:table>
                    </c:when>
                    <c:otherwise>
                        <display:table style="width:80%;" class="tablecloth" name="${actionBean.senaraiPemohon}"
                                       cellpadding="0" cellspacing="0" id="line1">
                            <c:if test="${edit}">
                                <display:column title="<input type='checkbox' id='semua_rm' name='semua_rm' onclick='javascript:selectAll(this,\"rm_\");'/>">
                                    <s:checkbox name="checkbox" id="rm_${line1_rowNum}" value="${line1.idPemohon}" class="rm_"/>
                                </display:column>
                            </c:if>
                            <display:column title="Bil">${line1_rowNum}</display:column>
                            <c:choose>
                              <c:when test="${empty line.nama}">
                                  <display:column  title="Nama" property="pihak.nama" class="nama"/>
                              </c:when>
                              <c:otherwise>
                                  <display:column  title="Nama" property="nama" class="nama"/>
                              </c:otherwise>
                          </c:choose>
                          <c:choose>
                              <c:when test="${empty line.noPengenalan}">
                                  <display:column property="pihak.noPengenalan" title="No Pengenalan" style="width:15%;"/>
                              </c:when>
                              <c:otherwise>
                                  <display:column property="noPengenalan" title="No Pengenalan" style="width:15%;"/>
                              </c:otherwise>
                          </c:choose>
                            <display:column title="Jenis Pihak" property="jenis.nama" class="remove"/>
                      <%--      <display:column title="Alamat Surat Menyurat">
                                ${line1.pihak.suratAlamat1}
                                <c:if test="${line1.pihak.suratAlamat1 ne null && line1.pihak.suratAlamat2 ne null}"> , </c:if>
                                ${line1.pihak.suratAlamat2}
                                <c:if test="${line1.pihak.suratAlamat2 ne null && line1.pihak.suratAlamat3 ne null}"> , </c:if>
                                ${line1.pihak.suratAlamat3}
                                <c:if test="${line1.pihak.suratAlamat3 ne null && line1.pihak.suratAlamat4 ne null}"> , </c:if>
                                ${line1.pihak.suratAlamat4}
                                <c:if test="${line1.pihak.suratAlamat4 ne null && line1.pihak.suratPoskod ne null}">,</c:if>
                                ${line1.pihak.suratPoskod}
                                <c:if test="${line1.pihak.suratPoskod ne null && line1.pihak.suratNegeri.kod ne null}">,</c:if>
                                ${line1.pihak.suratNegeri.nama}
                            </display:column>--%>
                            <c:if test="${edit}">
                                <display:column title="Kemaskini">
                                    <p align="center">
                                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="doEdit2('${line1.idPemohon}', 'pemohon','${line1.pihak.idPihak}','${line1.hakmilikPihak.idHakmilikPihakBerkepentingan}');return false;"
                                             onmouseover="this.style.cursor='pointer';">
                                    </p>
                                </display:column>
                                  <c:if test="${line1.jenis.kod eq 'PA' || line1.jenis.kod eq 'PP'}">
                                    <display:column title="Kemaskini Waris">
                                        <p align="center">
                                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="doEditWaris('${line1.idPemohon}', '${line1.jenis.kod}','${line1.pihak.idPihak}', '${line1.hakmilikPihak.idHakmilikPihakBerkepentingan}');return false;"
                                             onmouseover="this.style.cursor='pointer';">
                                            </p>
                                    </display:column>
                                </c:if> 
                            </c:if>
                        </display:table>

                    </c:otherwise>
                </c:choose>
            </div>
            <c:if test="${edit && (fn:length(actionBean.senaraiPemohon) > 0 || fn:length(actionBean.senaraiPermohonanAtasPihak) > 0)}">
                <p align="center">
                    <label></label>
                    <s:button name="delete" onclick="remove2(this.name, 'remove', 'pemohon', 'rm');" value="Hapus" class="btn"/>
                </p>
            </c:if>
        </fieldset>        
    </s:form>
</div>
