<%-- 
    Document   : carian_kelompok
    Created on : Apr 4, 2013, 10:09:54 PM
    Author     : afham
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">
    function openKelompok() {
        //        doBlockUI();
        window.open("${pageContext.request.contextPath}/pelupusan/permohonan_berkelompok?tambahKelompok", "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
    }

    function openBertindih() {
        //        doBlockUI();
        window.open("${pageContext.request.contextPath}/pelupusan/permohonan_berkelompok?tambahBertindih", "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
    }
    function editKelompok(idMohon, f) {
        doBlockUI();
        var q = $(f).serialize();
        var url = '${pageContext.request.contextPath}/pelupusan/permohonan_berkelompok?editKelompok&idMohon=' + idMohon;
        window.location = url + q;
        doUnBlockUI();
    }

    function editKelompok1(idMohon, frm) {
        var url = '${pageContext.request.contextPath}/pelupusan/permohonan_berkelompok?editKelompok&idMohon=' + idMohon;
        frm.action = url;
        frm.submit();
    }

    function deleteKelompok(idMohon, frm) {
        if (confirm("Adakah anda pasti?")) {
            var url = '${pageContext.request.contextPath}/pelupusan/permohonan_berkelompok?deleteKelompok&idMohon=' + idMohon;
            frm.action = url;
            frm.submit();
        }
    }

    function refreshKelompok() {
        var frm = document.forms.utilitiKelompok;
        var url = '${pageContext.request.contextPath}/pelupusan/permohonan_berkelompok?findKelompok';
        frm.action = url;
        frm.submit();
    }

    function tambahPermohonan() {
        //        doBlockUI();
        var idKelompok = $('#idKelompok').val();
        window.open("${pageContext.request.contextPath}/pelupusan/permohonan_berkelompok?tambahPermohonan&idKelompok=" + idKelompok, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
    }

    function popup(url)
    {
        params = 'width=900';
        params += ', height=900';
        params += ', top=200, left=100';
        params += ', directories=no';
        params += ', fullscreen=no';
        params += ', location=no';
        params += ', menubar=yes';
        params += ', resizable=yes';
        params += ', scrollbars=yes';
        params += ', status=yes';
        params += ', toolbar=yes';
        newwin = window.open(url, 'PopUp', params);
        if (window.focus) {
            newwin.focus()
        }
        return false;
    }

    function refreshPermohonanKelompok(val) {
        //        alert(val);
        var frm = document.forms.utilitiKelompok;
        var url = '${pageContext.request.contextPath}/pelupusan/permohonan_berkelompok?editKelompok&idMohon=' + val;
        frm.action = url;
        frm.submit();
    }

    function selectCheckBox()
    {
        var e = $('#sizePermohonan').val();
        var idKelompok = $('#idKelompok').val();
        var cnt = 0;
        var data = new Array();
        for (cnt = 0; cnt < e; cnt++)
        {
            if (e == '1') {
                if (document.utilitiKelompok.checkmate.checked) {
                    //                     alert(document.frm.checkmate[cnt].value) ;
                    data[cnt] = document.utilitiKelompok.checkmate.value;
                }

            }
            else {
                if (document.utilitiKelompok.checkmate[cnt].checked) {
                    //                     alert(document.frm.checkmate[cnt].value) ;
                    data[cnt] = document.utilitiKelompok.checkmate[cnt].value;
                }
                else {
                    data[cnt] = "T";
                }
            }
        }
        //                    alert(data) ;
        if (confirm("Adakah anda pasti?")) {
            var url = '${pageContext.request.contextPath}/pelupusan/permohonan_berkelompok?deletePermohonan&item=' + data;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                        self.refreshPermohonanKelompok(idKelompok);
                    }, 'html');
        }
    }

    function janaPermohonan() {
        var idKelompok = $('#idKelompok').val();
        if (confirm("Adakah anda pasti untuk jana permohonan?")) {
            var url = '${pageContext.request.contextPath}/pelupusan/permohonan_berkelompok?janaPermohonan&idKelompok=' + idKelompok;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                        self.refreshPermohonanKelompok(idKelompok);
                    }, 'html');
        }
    }
//    ===============================================================================================================================
    function filterJabatan(f, kodJabatan) {
//               alert(kodJabatan);
        var queryString = $(f).formSerialize();
        var url = "${pageContext.request.contextPath}/Statusbayaran?filterByJabatan" + queryString + "&kodJabatan=" + kodJabatan;
        $.get(url,
                function(data) {
                    $('#jab').html(data);
                }, 'html');
    }
    function clearText1(id) {
        $("#" + id + " select").each(function(el) {
            $(this).val('0');
        });
    }

    function cetak(f, id1) {
        var queryString = $(f).formSerialize()
        window.open("${pageContext.request.contextPath}/hasil/semak_dokumen?cetak&" + queryString + "&kodUrusan=" + id1, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }


//    ===============================================================================================================================

    function cetakDokumen(f, kod){
        var kodNegeri = document.getElementById('negeri');
        var form = $(f).formSerialize();
        var report =null;

        if(kodNegeri.value == 'melaka'){
            report = 'DISSrtPeringatanMCL_MLK.rdf';
        }else{
            report = 'DISSrtPeringatanMCL_MLK.rdf';
        }
        var url = "reportName="+report+"%26report_p_id_mohon="+kod;

        window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url="+url, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
        <%--window.open("${pageContext.request.contextPath}/reportGeneratorServlet?"+form+"&reportName="+report+"&report_p_kod_urusan="+kod, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700")--%>
    }
    
    function tolakPermohonan(idMohon){
            if (confirm("Adakah anda pasti untuk Tolak Permohonan?")) {
            var url = "${pageContext.request.contextPath}/pelupusan/utiliti/statusbayaran?tolakPermohonan&idPermohonan="+idMohon;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                        //self.refreshPermohonanKelompok(idKelompok);
                    }, 'html');
            }
        }
</script>
<div id="jab">
    <stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <stripes:errors />
    <stripes:form beanclass="etanah.view.stripes.pelupusan.utility.StatusBayaranActionbean" id="utilitiKelompok">
        <stripes:text name="kodNegeri" id="negeri" style="display:none;" />
        <div class="content">
            <%--
            <fieldset class="aras1">
                <legend>
                    Status Bayaran Belum Tamat Tempoh 
                </legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiBayaranBelumTamat}"
                                   id="line" style="width:95%"
                                   requestURI="${pageContext.request.contextPath}/pelupusan/utiliti/statusbayaran">                    
                        <display:column title="Bil" class="bil${line_rowNum}" style="${bcolor}">      
                            ${line_rowNum}
                        </display:column>
                        <display:column title="ID Permohonan">
                            <c:if test="${line.mohon.idPermohonan ne null}">
                                ${line.mohon.idPermohonan}
                            </c:if>
                        </display:column>
                        <display:column title="Urusan">
                            ${line.kodUrusan.nama}
                        </display:column>
                        <display:column title="Bandar/Pekan/Mukim">                        
                            ${line.kodBPM.nama}
                        </display:column>
                        <display:column title="Tarikh Terima Notis">                        
                            <stripes:format formatPattern="dd/MM/yyyy" value="${line.tarikhTerimaNotis}"/>
                        </display:column>
                        <display:column title="Tarikh Bayaran Akhir">                        
                            <stripes:format formatPattern="dd/MM/yyyy" value="${line.tarikhBayaranAkhir}"/>
                        </display:column>
                        <!--<label>Tarikh Tamat :</label>--> 
                        --%>
                        <%-- <c:choose>
                           <c:when test="${actionBean.hu.tarikhTamat ne null}">
                             <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.hu.tarikhTamat}"/>&nbsp;
                           </c:when>
                           <c:otherwise>
                             <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.permohonanRujukanLuar.tarikhTamat}"/>&nbsp;
                           </c:otherwise>
                         </c:choose>--%>
                        <%--
                        <display:column title="Tindakan">
                        --%>
                            <%--
                            <a onclick="editKelompok1('${line.mohon.idPermohonan}', document.forms.utilitiKelompok)" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/></a>
                            <a onclick="deleteKelompok('${line.mohon.idPermohonan}', document.forms.utilitiKelompok)" onmouseover="this.style.cursor = 'pointer';" ><img alt="Hapus" height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/not_ok.gif'/></a> 
                            --%>
                            <%--
                            </display:column>
                           



                    </display:table>
                    --%>           
                    <%--<table style="width:99.2%" bgcolor="green">
                        <tr>
                        <td align="right">
                            <stripes:button name=" " value="Cetak" class="btn" onclick="cetakDokumen(this.form, '${actionBean.ku.kod}');"/>
                        </td>
                        </tr>
                    </table>--%>
                </div>
            </fieldset>
             <fieldset class="aras1">
                <legend>
                    Status Bayaran Telah Tamat Tempoh 
                </legend>
                 <div class="content" align="center">
                      <display:table class="tablecloth" name="${actionBean.senaraiBayaranTamatTempoh}"
                                   id="line" style="width:95%"
                                   requestURI="${pageContext.request.contextPath}/pelupusan/utiliti/statusbayaran">                    
                        <display:column title="Bil" class="bil${line_rowNum}" style="${bcolor}">      
                            ${line_rowNum}
                        </display:column>
                        <display:column title="ID Permohonan">
                            <c:if test="${line.mohon.idPermohonan ne null}">
                                ${line.mohon.idPermohonan}
                            </c:if>
                        </display:column>
                        <display:column title="Urusan">
                            ${line.kodUrusan.nama}
                        </display:column>
                        <display:column title="Bandar/Pekan/Mukim">                        
                            ${line.kodBPM.nama}
                        </display:column>
                        <display:column title="Tarikh Terima Notis">                        
                            <stripes:format formatPattern="dd/MM/yyyy" value="${line.tarikhTerimaNotis}"/>
                        </display:column>
                        <display:column title="Tarikh Bayaran Akhir">                        
                            <stripes:format formatPattern="dd/MM/yyyy" value="${line.tarikhBayaranAkhir}"/>
                        </display:column>
                        <!--<label>Tarikh Tamat :</label>--> 

                        <%-- <c:choose>
                           <c:when test="${actionBean.hu.tarikhTamat ne null}">
                             <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.hu.tarikhTamat}"/>&nbsp;
                           </c:when>
                           <c:otherwise>
                             <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.permohonanRujukanLuar.tarikhTamat}"/>&nbsp;
                           </c:otherwise>
                         </c:choose>--%>
                        <display:column title="Tindakan">
                            <a onclick="cetakDokumen(document.forms.utilitiKelompok,'${line.mohon.idPermohonan}' )" onmouseover="this.style.cursor = 'pointer';"><img alt="Kemaskini"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/></a>
                            <a onclick="tolakPermohonan('${line.mohon.idPermohonan}')" onmouseover="this.style.cursor = 'pointer';" style="text-decoration: underline; color: #000">Tolak Permohonan</a>
                            </display:column>
                    </display:table> 
                 </div>
             </fieldset>
        </div>

    </stripes:form>
</div>
