<%--
    Document   : kemasukan_maklumat_asas
    Created on : 13 April 2011, 5:36:26 PM
    Author     : massita
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
    $(document).ready( function(){
        $(".wideselect")
        .mouseover(function(){
            $(this).data("origWidth", $(this).css("width")).css("width", "auto");
        })
        .mouseout(function(){
            $(this).css("width", $(this).data("origWidth"));
        });

        $("#noPu").val($("#hiddenNoPu").val());
        $("#syaratNyata").val($("#hiddenSyaratNyata").val());
        $("#sekatan").val($("#hiddenKodSekatan").val());

        $("#cariKodSyaratNyata").click(function(){
            var url ="${pageContext.request.contextPath}/pendaftaran/maklumat_asas?showFormCariKodSyaratNyata";
            window.open(url,"eTanah","status=1,toolbar=0,location=1,menubar=0,width=900,height=600");
        });

        $("#cariKodSekatan").click(function(){
            var url ="${pageContext.request.contextPath}/pendaftaran/maklumat_asas?showFormCariKodSekatan";
            window.open(url,"eTanah","status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=600");
        });

        if($("#pegangan").val()=='S'){
            $("#tempohPegangan").val('');
            $("#tempohPegangan").attr("disabled", true);
        }
        $("#pegangan").change( function() {
            if($("#pegangan").val()=='S'){
                $("#tempohPegangan").val('');
                $("#tempohPegangan").attr("disabled", true);
            }
            if($("#pegangan").val()=='P'){
                $("#tempohPegangan").attr("disabled", false);
            }
        });

    });
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.pengambilan.maklumatAsasPermohonanTerdahulu">
    <s:messages/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Asas</legend>
            <p>
                <label>Tarikh Daftar :</label>
                 <c:if test="${actionBean.hakmilik.tarikhDaftar ne null}"><s:format formatPattern="dd/MM/yyyy" value="${actionBean.hakmilik.tarikhDaftar}"/>&nbsp;</c:if>
                <c:if test="${actionBean.hakmilik.tarikhDaftar eq null}"> Tiada Data </c:if>
                <%--<fmt:formatDate type="date"
                                pattern="dd/MM/yyyy"
                                value="${actionBean.hakmilik.tarikhDaftar}"/>&nbsp;--%>
                <s:hidden name="tarikhDaftar"/>
                <%-- <s:text name="tarikhDaftar" class="datepicker" value="${actionBean.hakmilik.tarikhDaftar}"  formatType="date"/>--%>
            </p>
             <p>
                 <label>Jenis Hakmilik :</label>
                 ${actionBean.hakmilik.kodHakmilik.nama}&nbsp;
             </p>
             <p>
                 <label>Kategori :</label>
                 ${actionBean.hakmilik.kategoriTanah.nama}&nbsp;
             </p>
            <p>
                <label>Tanah Rizab :</label>
                <c:if test="${actionBean.hakmilik.rizab.kod ne null}">${actionBean.hakmilik.rizab.kod}&nbsp;</c:if>
                <c:if test="${actionBean.hakmilik.rizab.kod eq null}"> Tidak </c:if>
                ${actionBean.hakmilik.rizab.kod}
            </p>
            <p>
                <label>Pihak Berkuasa Tempatan :</label>
                ${actionBean.hakmilik.pbt.kod}
            </p>
            <p>
                <label>Taraf Pegangan :</label>
                <c:if test="${actionBean.hakmilik.pegangan eq 'S'}">
                SELAMA-LAMANYA
                </c:if>
                <c:if test="${actionBean.hakmilik.pegangan eq 'P'}">
                PAJAKAN
                </c:if>
            </p>
            <p>
                <label>Tempoh :</label>
                ${actionBean.hakmilik.tempohPegangan}&nbsp;Tahun
          </p>
            <p>
                <label>Tarikh Luput :</label>
                <c:if test="${actionBean.hakmilik.tarikhLuput eq null}">
                    Tiada
                </c:if>
                <c:if test="${actionBean.hakmilik.tarikhLuput ne null}">
                   <s:format formatPattern="dd/MM/yyyy" value="${actionBean.hakmilik.tarikhLuput}"/>&nbsp;
                </c:if>
            </p>
             <p>
                 <label>Syarat Nyata :</label>
                 <c:if test="${actionBean.hakmilik.syaratNyata.syarat ne null}"> ${actionBean.hakmilik.syaratNyata.syarat} </c:if>
                 <c:if test="${actionBean.hakmilik.syaratNyata.syarat eq null}"> Tiada Data </c:if>
                
             </p>
            <p>
                <label>Sekatan Kepentingan :</label>
                <c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan ne null}"> ${actionBean.hakmilik.sekatanKepentingan.sekatan} </c:if>
                 <c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan eq null}"> Tiada Data </c:if>
                
            </p>
            <p>
                <label>&nbsp;</label>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>

        </fieldset>
    </div>
</s:form>